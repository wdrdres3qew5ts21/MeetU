/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wdrdr
 */
@Document(collection = "posts")
public class Post {

    @Id
    private String postId;
    
    private String communityId;

    private String uid;

    private String displayName;
    
    private String photoURL;
    
    private String picture;

    private String postDetail;

    private PollChoices pollVoteChoices;

//    @DBRef
    private List<CommentOfPost> commentList;

    private Date postOfDate;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
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

    public List<CommentOfPost> getCommentList() {
        if(commentList == null){
            commentList = new ArrayList<>();
        }
        return commentList;
    }

    public void setCommentList(List<CommentOfPost> commentList) {
        this.commentList = commentList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
        return "Post{" + "postId=" + postId + ", userId=" + uid + ", userName=" + displayName + ", postDetail=" + postDetail + ", pollVoteChoices=" + pollVoteChoices + ", commentOfPost=" + commentList + ", postOfDate=" + postOfDate + '}';
    }

}
