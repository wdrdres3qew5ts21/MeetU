/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.controller;

import com.meetu.communityservice.service.CommunityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.model.Post;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wdrdr
 */
@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/communitys")
    public List<Community> findAllCommunityList(@RequestParam(required = false) String communityName) {
        if (communityName == null) {
            return communityService.findAllCommunityList();
        }
        return communityService.findByCommunityNameLike(communityName);
    }

    @PostMapping("/community")
    public ResponseEntity<Community> createCommunity(@RequestBody Community community) {
        return new ResponseEntity<Community>(communityService.createCommunity(community), HttpStatus.CREATED);
    }

    @GetMapping("/community/{communityId}/post/{postId}")
    public ResponseEntity<Post> getPostFromCommunityById(@PathVariable String communityId, @PathVariable String postId) {
        return new ResponseEntity<Post>(communityService.getPostFromCommunityById(communityId, postId), HttpStatus.OK);
    }

    @PostMapping("/community/{communityId}")
    public ResponseEntity<Post> createPostToCommunity(@RequestParam String communityId, @RequestBody Post newPostOfCommunity) {
        return new ResponseEntity<Post>(communityService.createPostToCommunity(communityId, newPostOfCommunity), HttpStatus.CREATED);
    }

//    @PostMapping("/community/{communityId}/post/{postId}")
//    public ResponseEntity<Post> addCommentToPostOfCommunity(@RequestParam String communityId, @RequestParam String postId, @RequestBody Post newCommentOfPost) {
//        return new ResponseEntity<Post>(communityService.addCommentToPostOfCommunity(communityId, postId, newCommentOfPost), HttpStatus.CREATED);
//    }
}
