/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.service;

import com.meetu.communityservice.model.CommentOfPost;
import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.model.Post;
import com.meetu.communityservice.repository.CommunityRepository;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class CommunityService {
    
    @Autowired
    private CommunityRepository communityRepository;
    
    public List<Community> findAllCommunityList() {
        return communityRepository.findAll();
    }
    
    public List<Community> findByCommunityNameLike(String communityName) {
        return communityRepository.findByCommunityNameIgnoreCaseLike(communityName);
    }
    
    public ResponseEntity<Community> createCommunity(Community community) {
        return null;
    }
    
    public Post createPostToCommunity(String communityId, Post newPostOfCommunity) {
        Community community = communityRepository.findById(communityId).get();
        newPostOfCommunity.setPostId(new ObjectId().toString());
        newPostOfCommunity.setPostOfDate(new Date());
        community.getPostLists().add(newPostOfCommunity);
        communityRepository.save(community);
        return newPostOfCommunity;
    }
    
    public Post addCommentToPostOfCommunity(String communityId, String postId, CommentOfPost commentOfPost) {
        Community community = communityRepository.findById(communityId).get();
        Post postForAddComment = findPostFromComunityByPostId(community, postId);
        commentOfPost.setCommentOfPostId(new ObjectId().toString());
        commentOfPost.setCommentOfPostDate(new Date());
        postForAddComment.getCommentOfPost().add(commentOfPost);
        communityRepository.save(community);
        return postForAddComment;
    }
    
    public Post getPostFromCommunityById(String communityId, String postId) {
        Community community = communityRepository.findById(communityId).get();
        return findPostFromComunityByPostId(community, postId);
    }
    
    public Post findPostFromComunityByPostId(Community community, String postId) {
        Post postOfCommunity = null;
        for (int i = 0; i < community.getPostLists().size(); i++) {
            Post postOfEachLoop = community.getPostLists().get(i);
            if (postOfEachLoop.getPostId().equals(postId)) {
                postOfCommunity = postOfEachLoop;
            }
        }
        return postOfCommunity;
    }
}
