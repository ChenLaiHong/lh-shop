package com.lh.api.product;

import com.lh.entity.Product;
import com.lh.shop.common.pojo.ResultBean;

import java.util.List;

/**
 * Created by laiHom on 2020/3/8.
 */
public interface ISearchService {
    ResultBean initAllData();

    List<Product> searchByKeyWord(String keyWord);
}
