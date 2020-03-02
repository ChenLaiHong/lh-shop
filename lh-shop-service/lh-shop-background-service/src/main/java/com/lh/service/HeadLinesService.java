package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lh.api.product.IHeadLinesService;
import com.lh.entity.HeadLines;
import com.lh.entity.HeadLinesExample;
import com.lh.mapper.HeadLinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;
import static com.lh.shop.common.util.StringUtil.findPath;

/**
 * Created by laiHom on 2020/2/23.
 */
@Component
@Service
public class HeadLinesService implements IHeadLinesService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private HeadLinesMapper headLinesMapper;

    @Override
    public List<HeadLines> pageList(Map<String, Object> map) {
        return headLinesMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return headLinesMapper.getTotal(map);
    }

    @Override
    public HeadLines findById(Integer newsId) {
        return headLinesMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public int add(HeadLines headLines) {
        headLines.setState(1);
        headLines.setUpdateTime(new Date());

        return headLinesMapper.insertSelective(headLines);
    }

    @Override
    public int update(HeadLines headLines) {

        headLines.setImageUrl(headLines.getImageUrl());
        headLines.setUpdateTime(new Date());
        return headLinesMapper.updateByPrimaryKeyWithBLOBs(headLines);
    }

    @Override
    public void delete(String ids) {

        headLinesMapper.updateList(StringIds(ids));
    }

    @Override
    public List<HeadLines> getFive() {

        HeadLinesExample headLinesExample = new HeadLinesExample();
        headLinesExample.createCriteria().andStateEqualTo(1);

        headLinesExample.setOrderByClause("update_time DESC");
        List<HeadLines> headLinesList = headLinesMapper.selectByExample(headLinesExample);
        if(headLinesList.size() >= 5){
            return headLinesList.subList(0,5);
        }else {
            return headLinesList;
        }
    }
}
