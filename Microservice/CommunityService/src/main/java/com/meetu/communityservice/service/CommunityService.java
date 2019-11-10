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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author wdrdr
 */
@Service
public class CommunityService {
    
    @Value("${event.service}")
    private String EVENTSERVICE_URL;

    @Value("${user.service}")
    private String USERSERVICE_URL;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private CommunityRepository communityRepository;
    
    public Page<Community> findAllCommunityList(int page, int contTentPerPage) {
        return communityRepository.findAll(PageRequest.of(page, contTentPerPage));
    }
    
    public Page<Community> findByCommunityNameLike(String communityName,int page, int contTentPerPage) {
        return communityRepository.findByCommunityNameIgnoreCaseLike(communityName, PageRequest.of(page, page, Sort.Direction.DESC, "communityId"));
    }
    
    public ResponseEntity<Community> createCommunity(Community community) {
        Community communityInDatabase = communityRepository.findByCommunityNameIgnoreCase(community.getCommunityName());
        if(communityInDatabase == null){
            communityRepository.save(community);
        }
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

    public ResponseEntity findAllCommunityThatUserSubscribe(String uid, int page, int contentPerPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
