/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 *
 * @author wdrdr
 */
public class Post {
    
    @Id
    @Indexed(direction = IndexDirection.ASCENDING)
    private String postId;
    
    private String userId;
    
    private String userName;
    
    private String postDetail;
    
    private List<String> commentOfPost;
    
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

    public List<String> getCommentOfPost() {
        return commentOfPost;
    }

    public void setCommentOfPost(List<String> commentOfPost) {
        this.commentOfPost = commentOfPost;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", userId=" + userId + ", userName=" + userName + ", postDetail=" + postDetail + ", commentOfPost=" + commentOfPost + '}';
    }
    
}
