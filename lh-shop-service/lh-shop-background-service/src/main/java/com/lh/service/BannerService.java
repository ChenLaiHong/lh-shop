package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IBannerService;
import com.lh.entity.Banner;
import com.lh.entity.BannerExample;
import com.lh.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/2/22.
 */
@Component
@Service
public class BannerService implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> pageList(Map<String, Object> map) {
        return bannerMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return bannerMapper.getTotal(map);
    }

    @Override
    public Banner findById(Integer bannerId) {
        return bannerMapper.selectByPrimaryKey(bannerId);
    }

    @Override
    public int add(Banner banner) {
        banner.setState(1);
        banner.setUpdateTime(new Date());
        return bannerMapper.insertSelective(banner);
    }

    @Override
    public int update(Banner banner) {
        banner.setUpdateTime(new Date());
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void delete(String ids) {
        bannerMapper.updateList(StringIds(ids));
    }

    @Override
    public List<Banner> getAll() {
        BannerExample bannerExample = new BannerExample();
        bannerExample.createCriteria().andStateEqualTo(1);
        return bannerMapper.selectByExample(bannerExample);
    }
}
