package com.lh.api.product;

import com.lh.entity.HeadLines;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/23.
 */
public interface IHeadLinesService {
    List<HeadLines> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    HeadLines findById(Integer newsId);

    int add(HeadLines headLines);

    int update(HeadLines headLines);

    void delete(String ids);

    List<HeadLines> getFive();
}
