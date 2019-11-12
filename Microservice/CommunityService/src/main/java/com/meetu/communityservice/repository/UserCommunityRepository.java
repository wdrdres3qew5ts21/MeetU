/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.repository;

import com.meetu.communityservice.model.UserCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Test
 */

public interface UserCommunityRepository extends MongoRepository<UserCommunity, String>{
    
    public Page<UserCommunity> findByUid(String uid, Pageable pageable);
    
    public UserCommunity findByUidAndCommunityId(String uid, String communityId);
    
    public void deleteById(String communityId);

    public void deleteByCommunityId(String userCommunityId);
    
}
