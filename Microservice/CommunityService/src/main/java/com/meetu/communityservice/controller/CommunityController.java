/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.controller;

import com.meetu.communityservice.model.CommentOfPost;
import com.meetu.communityservice.service.CommunityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wdrdr
 */
@CrossOrigin("*")
@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/communitys")
    public Page<Community> findAllCommunityList(
            @RequestParam(required = false) String communityName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int contentPerPage) {
        if (communityName == null) {
            return communityService.findAllCommunityList(page, contentPerPage);
        }
        return communityService.findByCommunityNameLike(communityName,page, contentPerPage);
    }

    @GetMapping("/communitys/user/{uid}")
    public ResponseEntity findAllCommunityThatUserSubscribe(
            @PathVariable String uid,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int contentPerPage) {
        return communityService.findAllCommunityThatUserSubscribe(uid, page, contentPerPage);
    }

    @PostMapping("/community")
    public ResponseEntity createCommunity(@RequestBody Community community) {
        return communityService.createCommunity(community);
    }

    @GetMapping("/community/{communityId}/post/{postId}")
    public ResponseEntity<Post> getPostFromCommunityById(@PathVariable String communityId, @PathVariable String postId) {
        return new ResponseEntity<Post>(communityService.getPostFromCommunityById(communityId, postId), HttpStatus.OK);
    }

    @PostMapping("/community/{communityId}/post")
    public ResponseEntity<Post> createPostToCommunity(@PathVariable String communityId, @RequestBody Post newPostOfCommunity) {
        return new ResponseEntity<Post>(communityService.createPostToCommunity(communityId, newPostOfCommunity), HttpStatus.CREATED);
    }

    @PostMapping("/community/{communityId}/post/{postId}")
    public ResponseEntity<Post> addCommentToPostOfCommunity(@PathVariable String communityId, @PathVariable String postId, @RequestBody CommentOfPost commentOfPost) {
        return new ResponseEntity<Post>(communityService.addCommentToPostOfCommunity(communityId, postId, commentOfPost), HttpStatus.CREATED);
    }

}
