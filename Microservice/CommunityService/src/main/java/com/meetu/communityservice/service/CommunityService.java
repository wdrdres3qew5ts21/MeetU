/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.service;

import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.repository.CommunityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class CommunityService {
    
    @Autowired
    private CommunityRepository communityRepository;
    
    public List<Community> findAllCommunityList(){
        return communityRepository.findAll();
    }

    public List<Community> findByCommunityNameLike(String communityName) {
        return communityRepository.findByCommunityNameIgnoreCaseLike(communityName);
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }
    
    
}
