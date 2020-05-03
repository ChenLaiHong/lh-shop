package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IBrowseRecordService;
import com.lh.entity.BrowseRecord;
import com.lh.entity.BrowseRecordExample;
import com.lh.mapper.BrowseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by laiHom on 2020/4/5.
 */
@Component
@Service
public class BrowseRecordService implements IBrowseRecordService {
    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    @Override
    public Boolean insertOrUpdate(Integer userId, Integer productId) {
        BrowseRecordExample browseRecordExample = new BrowseRecordExample();
        browseRecordExample.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
        List<BrowseRecord> browseRecordList = browseRecordMapper.selectByExample(browseRecordExample);
        //当数据库还没有记录该用户对该商品的浏览时执行添加操作
        if(browseRecordList.isEmpty()){
            //如果没有浏览记录
            BrowseRecord browseRecord = new BrowseRecord();
            browseRecord.setFrequency(1);
            browseRecord.setUserId(userId);
            browseRecord.setProductId(productId);
            Date dt = new Date();
            SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMddhhMMss");
            String date = matter1.format(dt);
            browseRecord.setBrowseTime(date);
            Integer insert = browseRecordMapper.insertSelective(browseRecord);
            if (insert > 0) {
                return true;
            } else {
                return false;
            }
        }else {
            //如果有记录那么就更新
            BrowseRecord browseRecord = browseRecordList.get(0);
            browseRecord.setFrequency(browseRecord.getFrequency() + 1);
            Date dt = new Date();
            SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMddhhMMss");
            String date = matter1.format(dt);
            browseRecord.setBrowseTime(date);
            Integer insert = browseRecordMapper.updateByPrimaryKey(browseRecord);
            if (insert > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<BrowseRecord> getList() {
        return browseRecordMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getProductIds(Integer userId) {
        return browseRecordMapper.getProductIds(userId);
    }
}
