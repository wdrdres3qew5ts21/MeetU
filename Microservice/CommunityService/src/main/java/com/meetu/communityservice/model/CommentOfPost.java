/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import org.bson.types.ObjectId;

/**
 *
 * @author wdrdr
 */
public class CommentOfPost {

    private String commentOfPostId;

    private String userId;

    private String userName;

    private String commentOfPostDetail;

    public String getCommentOfPostId() {
        return commentOfPostId;
    }

    public void setCommentOfPostId(String commentOfPostId) {
        this.commentOfPostId = commentOfPostId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentOfPostDetail() {
        return commentOfPostDetail;
    }

    public void setCommentOfPostDetail(String commentOfPostDetail) {
        this.commentOfPostDetail = commentOfPostDetail;
    }

    @Override
    public String toString() {
        return "CommentOfPost{" + "commentOfPostId=" + commentOfPostId + ", userId=" + userId + ", userName=" + userName + ", commentOfPostDetail=" + commentOfPostDetail + '}';
    }

}
