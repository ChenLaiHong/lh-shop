package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer commentId;

    private String commentLevel;

    private String comment;

    private Date commentTime;

    private Integer userId;

    private Integer itemId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(String commentLevel) {
        this.commentLevel = commentLevel == null ? null : commentLevel.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}