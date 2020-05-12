package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ICommentService;
import com.lh.entity.Comment;
import com.lh.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by laiHom on 2020/5/10.
 */
@Component
@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        comment.setCommentTime(new Date());
        return commentMapper.insertSelective(comment);
    }
}
