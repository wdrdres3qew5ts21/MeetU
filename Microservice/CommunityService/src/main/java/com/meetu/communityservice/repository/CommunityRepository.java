/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.repository;

import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author wdrdr
 */
public interface CommunityRepository extends MongoRepository<Community, String>{

    public Page<Community> findByCommunityNameIgnoreCaseLikeAndInterestTagsIsIn(String communityName, String[] interestTags,Pageable pageable);
    
    public Community findByCommunityNameIgnoreCase(String communityName);

//    public Post findByCommunityIdAndPostListsPostId(String communityId, String post);
    
    public Community findByCommunityIdAndPostListsPostId(String communityId, String post);

    public Page<Community> findByCommunityNameIgnoreCaseLike(String communityName, PageRequest of);

    public Page<Community> findByInterestTagsIsIn(String[] interestTags, PageRequest of);

    public Optional<Community> findByCommunityId(String communityId);
    
}
