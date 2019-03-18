/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.repository;

import com.meetu.communityservice.model.Community;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author wdrdr
 */
public interface CommunityRepository extends MongoRepository<Community, String>{

    public List<Community> findByCommunityNameIgnoreCaseLike(String communityName);
    
}
