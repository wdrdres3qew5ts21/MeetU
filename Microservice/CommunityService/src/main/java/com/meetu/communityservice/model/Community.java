/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "communitys")
public class Community {

    @Id
    @Indexed(direction = IndexDirection.ASCENDING)
    private String communityId;

    private String communityName;

    private String communityDescription;

    private String communityImageCover;

    private List<String> interestTags;
   
    private List<User> userLists;

    private List<Post> postLists;

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

    public String getCommunityDescription() {
        return communityDescription;
    }

    public void setCommunityDescription(String communityDescription) {
        this.communityDescription = communityDescription;
    }

    public String getCommunityImageCover() {
        return communityImageCover;
    }

    public void setCommunityImageCover(String communityImageCover) {
        this.communityImageCover = communityImageCover;
    }

    public List<String> getInterestTags() {
        return interestTags;
    }

    public void setInterestTags(List<String> interestTags) {
        this.interestTags = interestTags;
    }

    public List<User> getUserLists() {
        return userLists;
    }

    public void setUserLists(List<User> userLists) {
        this.userLists = userLists;
    }

    public List<Post> getPostLists() {
        return postLists;
    }

    public void setPostLists(List<Post> postLists) {
        this.postLists = postLists;
    }

    @Override
    public String toString() {
        return "Community{" + "communityId=" + communityId + ", communityName=" + communityName + ", communityDescription=" + communityDescription + ", communityImageCover=" + communityImageCover + ", interestTags=" + interestTags + ", userLists=" + userLists + ", postLists=" + postLists + '}';
    }

}
