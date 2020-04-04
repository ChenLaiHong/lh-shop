package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.pojo.CartItem;
import com.lh.api.product.ICartService;
import com.lh.api.vo.CartItemVO;
import com.lh.entity.ProductSpecs;
import com.lh.mapper.ProductSpecsMapper;
import com.lh.shop.common.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/18.
 */
@Component
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Override
    public ResultBean add(String key, Integer productId, Integer count) {
        //1.根据key，来寻找购物车
        List<CartItem> cart = (List<CartItem>) redisTemplate.opsForValue().get(key);
        //2.购物车不存在
        if (cart == null) {
            cart = new ArrayList<>();
            cart.add(new CartItem(productId, count, new Date()));
            //保存到redis中,刷新有效期
            return resetToReids(key, cart);
        }
        //3.购物车存在
        //遍历查看当前添加的商品是否在购物车里面
        for (CartItem cartItem : cart) {
            if (cartItem.getProductId().longValue() == productId.longValue()) {
                //商品已存在的情况
                cartItem.setCount(cartItem.getCount() + count);
                cartItem.setUpdateTime(new Date());
                //保存到redis中,刷新有效期
                return resetToReids(key, cart);
            }
        }
        //商品不存在购物车中
        cart.add(new CartItem(productId, count, new Date()));
        //保存到redis中,刷新有效期
        return resetToReids(key, cart);
    }

    @Override
    public ResultBean updateCount(String key, Integer productId, Integer count) {
        //1.获取购物车
        List<CartItem> carts = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if (carts != null) {
            //2.遍历更新数量
            for (CartItem cartItem : carts) {
                if (productId.longValue() == cartItem.getProductId().longValue()) {
                    cartItem.setCount(count);
                    cartItem.setUpdateTime(new Date());
                    //写会redis
                    return resetToReids(key, carts);
                }
            }
        }
        return ResultBean.error("更新失败！");
    }

    @Override
    public ResultBean del(String key, Integer productId) {
        //1.获取到购物车
        List<CartItem> carts = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if (carts != null) {
            for (CartItem cartItem : carts) {
                if (productId.longValue() == cartItem.getProductId().longValue()) {
                    carts.remove(cartItem);
                    return resetToReids(key, carts);
                }
            }
        }
        return ResultBean.error("删除失败！");
    }

    private ResultBean resetToReids(String key, List<CartItem> carts) {
        redisTemplate.opsForValue().set(key, carts);
        redisTemplate.expire(key, 30, TimeUnit.DAYS);
        return ResultBean.success(String.valueOf(carts.size()));
    }

    @Override
    public ResultBean list(String key) {
        List<CartItem> cartItems = (List<CartItem>) redisTemplate.opsForValue().get(key);
        //List<CartItem>--->List<CartItemVO>
        if (cartItems == null) {
            return ResultBean.error("您当前购物车还没有添加商品信息！");
        }
        List<CartItemVO> cartItemVOS = new ArrayList<>(cartItems.size());
        for (CartItem cartItem : cartItems) {
            CartItemVO vo = new CartItemVO();
            vo.setCount(cartItem.getCount());
            vo.setUpdateTime(cartItem.getUpdateTime());
            //缓存这里发挥作用
            //前提条件：系统提前做好热门商品的预热，根据商品的访问量，将20%的商品进行缓存
            //key----------value
            //product:101---productInfo
            StringBuilder stringBuilder = new StringBuilder("product:").append(cartItem.getProductId());
            ProductSpecs product = (ProductSpecs) redisTemplate.opsForValue().get(stringBuilder.toString());
            if (product == null) {
                //查询数据库, 说明不是热门商品
                product = productSpecsMapper.selectByPrimaryKey(cartItem.getProductId());
                //缓存以下，设置有效期
                redisTemplate.opsForValue().set(stringBuilder.toString(), product);
                redisTemplate.expire(stringBuilder.toString(), 60, TimeUnit.MINUTES);
            }
            vo.setProductSpecs(product);
            cartItemVOS.add(vo);
        }
        //cartItemVOS-->
        Collections.sort(cartItemVOS, new Comparator<CartItemVO>() {
            @Override
            public int compare(CartItemVO o1, CartItemVO o2) {
                return (int) (o2.getUpdateTime().getTime() - o1.getUpdateTime().getTime());
            }
        });

        return new ResultBean(200, cartItemVOS);
    }

    @Override
    public ResultBean merge(String noLoginKey, String loginKey) {
        //1.获取未登录购物车
        List<CartItem> noLoginCart = (List<CartItem>) redisTemplate.opsForValue().get(noLoginKey);
        if (noLoginCart == null) {
            return ResultBean.success("不存在未登录购物车，无需合并！");
        }
        //2.获取已登录购物车
        List<CartItem> loginCart = (List<CartItem>) redisTemplate.opsForValue().get(loginKey);
        if (loginCart == null) {
            //不存在已登录购物车
            redisTemplate.opsForValue().set(loginKey, noLoginCart);
            return resetToReids(loginKey, noLoginCart);
        }
        //3.两者都存在，需要合并两辆购物车
        //hashMap key productId--->cartItem
        HashMap<Integer, CartItem> map = new HashMap<>();
        for (CartItem cartItem : noLoginCart) {
            map.put(cartItem.getProductId(), cartItem);
        }
        for (CartItem cartItem : loginCart) {
            CartItem item = map.get(cartItem.getProductId());
            if (item == null) {
                map.put(cartItem.getProductId(), cartItem);
            } else {
                item.setCount(cartItem.getCount() + item.getCount());
            }
        }
        //hashMap--->list
        Collection<CartItem> values = map.values();
        List<CartItem> list = new ArrayList<>(values);
        //将list写到已登录购物车
        ResultBean resultBean = resetToReids(loginKey, list);
        //删除未登录购物车
        redisTemplate.delete(noLoginKey);
        return resultBean;
    }

    @Override
    public ResultBean delIds(String s, String ids) {

        List<Integer> list = StringIds(ids);
        //1.获取到购物车
        List<CartItem> carts = (List<CartItem>) redisTemplate.opsForValue().get(s);
        if (carts != null) {
            for (int i = 0; i < list.size(); i++) {
                for (CartItem cartItem : carts) {
                    if (list.get(i) == cartItem.getProductId().intValue()) {
                        carts.remove(cartItem);
                        break;
                    }
                }
            }
            return resetToReids(s, carts);
        }
        return ResultBean.error("删除失败！");
    }
}
