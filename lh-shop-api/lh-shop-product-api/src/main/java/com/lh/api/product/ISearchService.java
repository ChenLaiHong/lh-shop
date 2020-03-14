package com.lh.api.product;

import com.lh.entity.Product;
import com.lh.shop.common.pojo.PageResultBean;
import com.lh.shop.common.pojo.ResultBean;

import java.util.List;

/**
 * Created by laiHom on 2020/3/8.
 */
public interface ISearchService {
    ResultBean initAllData();

    List<Product> searchByKeyWord(String keyWord);

    PageResultBean<Product> searchByKeyWord(String keyWord, Integer pageIndex, Integer rows);

    ResultBean updateById(int resultTotal);
}
