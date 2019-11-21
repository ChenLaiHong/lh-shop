package com.lh.shop.common.util;

import com.lh.entity.ProductType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiHom on 2019/11/10.
 */
public class TreeUtils {

    public static List<ProductType> transProductType(List<ProductType> rootList){
        List<ProductType> list = new ArrayList();
        List<ProductType> finalList = new ArrayList();
        for (ProductType p : rootList) {
            if (p.getIsLeaf()==0 ){//表明是父类
                list.add(p);
            }
            p.setProductTypeList(setChild(p.getId(),rootList));

        }

        for (ProductType l : list){
            finalList.add(l);
            if(l.getProductTypeList() != null) {
                finalList.addAll(l.getProductTypeList());
            }
        }
        return finalList;
    }
    public static List<ProductType> setChild(Long id, List<ProductType> list ){
        List<ProductType> childList = new ArrayList();
        for (ProductType p : list) {
            if (id == p.getParentid() && p.getIsLeaf() != 0){
                childList.add(p);


            }
        }
        for (ProductType p : childList) {
            p.setProductTypeList(setChild(p.getId(),list));
        }
        if (childList.size()==0){
            return null;
        }
        return childList;
    }

}
