/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document("userCommunitys")
public class UserCommunity {

    @Id
    private String id;

    private String userCommunityId;

    private String photoURL;

    private String displayName;

    private String uid;

    private String communityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserCommunityId() {
        return userCommunityId;
    }

    public void setUserCommunityId(String userCommunityId) {
        this.userCommunityId = userCommunityId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "UserCommunity{" + "userCommunity=" + userCommunityId + ", photoURL=" + photoURL + ", dispalyName=" + displayName + ", uid=" + uid + ", communityId=" + communityId + '}';
    }

}
