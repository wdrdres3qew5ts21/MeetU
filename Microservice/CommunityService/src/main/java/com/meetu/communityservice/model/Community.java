/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "communitys")
public class Community {

    @Id
    private String id;
    
    @Indexed(direction = IndexDirection.ASCENDING)
    private String communityId;

    @NotNull(message = "Community Name Can't be Empty")
    private String communityName;

    private String communityDetail;

    private String communityPictureCover;

    private List<String> interestTags;
   
    private List<String> userLists;
    
    @DBRef(lazy = true)
    private List<Post> postLists;
    
    @NotNull(message = "Community Owner can't be Null")
    private User communityOwner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
    
    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public User getCommunityOwner() {
        return communityOwner;
    }

    public void setCommunityOwner(User communityOwner) {
        this.communityOwner = communityOwner;
    }
    
    public String getCommunityDetail() {
        return communityDetail;
    }

    public void setCommunityDetail(String communityDetail) {
        this.communityDetail = communityDetail;
    }

    public String getCommunityPictureCover() {
        return communityPictureCover;
    }

    public void setCommunityPictureCover(String communityPictureCover) {
        this.communityPictureCover = communityPictureCover;
    }

    public List<String> getInterestTags() {
        if(interestTags == null){
            interestTags = new ArrayList<>();
            return interestTags;
        }
        return interestTags;
    }

    public void setInterestTags(List<String> interestTags) {
        this.interestTags = interestTags;
    }

    public List<String> getUserLists() {
        if(userLists == null){
            userLists = new ArrayList<>();
            return userLists;
        }
        return userLists;
    }

    public void setUserLists(List<String> userLists) {
        this.userLists = userLists;
    }

    public List<Post> getPostLists() {
        if(postLists == null){
            postLists = new ArrayList<>();
            return postLists;
        }
        return postLists;
    }

    public void setPostLists(List<Post> postLists) {
        this.postLists = postLists;
    }

    @Override
    public String toString() {
        return "Community{" + "communityId=" + communityId + ", communityName=" + communityName + ", communityDescription=" + communityDetail + ", communityImageCover=" + communityPictureCover + ", interestTags=" + interestTags + ", userLists=" + userLists + ", postLists=" + postLists + '}';
    }

}
