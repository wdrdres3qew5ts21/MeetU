/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.repository;

import com.meetu.communityservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wdrdr
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    public Page<Post> findByCommunityId(String communityId, PageRequest of);
    
}
