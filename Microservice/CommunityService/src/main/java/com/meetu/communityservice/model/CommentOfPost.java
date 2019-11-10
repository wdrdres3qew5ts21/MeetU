/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document("comments")
public class CommentOfPost {

    @Id
    private String commentOfPostId;
    
    private String postId;

    private String uid;

    private String displayName;
    
    private String photoURL;
    
    private String picture;

    private String commentOfPostDetail;
    
    private Date commentOfPostDate;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getCommentOfPostId() {
        return commentOfPostId;
    }

    public void setCommentOfPostId(String commentOfPostId) {
        this.commentOfPostId = commentOfPostId;
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

    public String getCommentOfPostDetail() {
        return commentOfPostDetail;
    }

    public void setCommentOfPostDetail(String commentOfPostDetail) {
        this.commentOfPostDetail = commentOfPostDetail;
    }

    public Date getCommentOfPostDate() {
        return commentOfPostDate;
    }

    public void setCommentOfPostDate(Date commentOfPostDate) {
        this.commentOfPostDate = commentOfPostDate;
    }

    @Override
    public String toString() {
        return "CommentOfPost{" + "commentOfPostId=" + commentOfPostId + ", userId=" + uid + ", userName=" + displayName + ", commentOfPostDetail=" + commentOfPostDetail + ", commentOfPostDate=" + commentOfPostDate + '}';
    }
    

}
