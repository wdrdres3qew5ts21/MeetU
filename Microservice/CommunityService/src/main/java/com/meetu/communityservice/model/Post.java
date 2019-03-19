/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wdrdr
 */
@Document
public class Post {

    private String postId;

    private String userId;

    private String userName;

    private String postDetail;

    private PollChoices pollVoteChoices;

    private List<CommentOfPost> commentOfPost;

    private Date postOfDate;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public List<CommentOfPost> getCommentOfPost() {
        return commentOfPost;
    }

    public void setCommentOfPost(List<CommentOfPost> commentOfPost) {
        this.commentOfPost = commentOfPost;
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

    public PollChoices getPollVoteChoices() {
        return pollVoteChoices;
    }

    public void setPollVoteChoices(PollChoices pollVoteChoices) {
        this.pollVoteChoices = pollVoteChoices;
    }

    public Date getPostOfDate() {
        return postOfDate;
    }

    public void setPostOfDate(Date postOfDate) {
        this.postOfDate = postOfDate;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", userId=" + userId + ", userName=" + userName + ", postDetail=" + postDetail + ", pollVoteChoices=" + pollVoteChoices + ", commentOfPost=" + commentOfPost + ", postOfDate=" + postOfDate + '}';
    }

}
