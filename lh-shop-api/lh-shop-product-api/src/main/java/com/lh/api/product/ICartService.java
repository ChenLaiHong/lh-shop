package com.lh.api.product;

import com.lh.shop.common.pojo.ResultBean;

/**
 * Created by laiHom on 2020/3/18.
 */
public interface ICartService {
    //添加，关键的参数，商品id，购买的数量--跟业务相关的
    //跟实现方案细节相关的 key 确定购物车
    public ResultBean add(String key, Integer productId, Integer count);
    //更新数量
    public ResultBean updateCount(String key,Integer productId,Integer count);
    //删除
    public ResultBean del(String key,Integer productId);
    //查询
    public ResultBean list(String key);

    //合并购物车
    public ResultBean merge(String noLoginKey,String loginKey);
}
