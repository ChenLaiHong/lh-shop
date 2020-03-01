package com.lh.api.product;

import com.lh.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/22.
 */
public interface IBannerService {
    List<Banner> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    Banner findById(Integer bannerId);

    int add(Banner banner);

    int update(Banner banner);

    void delete(String ids);

    List<Banner> getAll();
}
