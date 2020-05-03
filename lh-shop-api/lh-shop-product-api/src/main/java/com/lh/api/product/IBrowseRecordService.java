package com.lh.api.product;

import com.lh.entity.BrowseRecord;

import java.util.List;

/**
 * Created by laiHom on 2020/4/5.
 */
public interface IBrowseRecordService {
    Boolean insertOrUpdate(Integer userId, Integer productId);

    List<BrowseRecord> getList();

    List<Integer> getProductIds(Integer userId);
}
