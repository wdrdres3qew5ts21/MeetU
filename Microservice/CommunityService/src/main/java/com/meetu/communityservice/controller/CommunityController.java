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
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
