/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.meetu.communityservice.model.CommentOfPost;
import com.meetu.communityservice.model.Community;
import com.meetu.communityservice.model.Post;
import com.meetu.communityservice.model.User;
import com.meetu.communityservice.model.UserCommunity;
import com.meetu.communityservice.repository.CommunityRepository;
import com.meetu.communityservice.repository.PostRepository;
import com.meetu.communityservice.repository.UserCommunityRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.client.HttpStatusCodeException;
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

    @Autowired
    private UserCommunityRepository userCommunityRepository;

    @Autowired
    private PostRepository postRepository;

    public Page<Community> findAllCommunityList(int page, int contTentPerPage) {
        return communityRepository.findAll(PageRequest.of(page, contTentPerPage));
    }

    public ResponseEntity findByCommunityNameLike(String communityName, String[] interestTags, int page, int contentPerPage) {
        if (interestTags == null & (communityName == null || communityName.isEmpty())) {
            Page<Community> allCommunity = communityRepository.findAll(PageRequest.of(page, contentPerPage, Sort.Direction.DESC, "communityId"));
            return ResponseEntity.status(HttpStatus.OK).body(allCommunity);
        } else if (interestTags != null & !communityName.isEmpty()) {
            Page<Community> communityFilterByTagsAndName = communityRepository.findByCommunityNameIgnoreCaseLikeAndInterestTagsIsIn(communityName, interestTags, PageRequest.of(page, contentPerPage));
            return ResponseEntity.status(HttpStatus.OK).body(communityFilterByTagsAndName);
        } else if (!communityName.isEmpty()) {
            Page<Community> communityFilterByName = communityRepository.findByCommunityNameIgnoreCaseLike(communityName, PageRequest.of(page, contentPerPage));
            return ResponseEntity.status(HttpStatus.OK).body(communityFilterByName);
        }
        Page<Community> findByInterestTagsIsIn = communityRepository.findByInterestTagsIsIn(interestTags, PageRequest.of(page, contentPerPage));
        return ResponseEntity.status(HttpStatus.OK).body(findByInterestTagsIsIn);
    }

    public ResponseEntity createCommunity(String token, Community community) {
        System.out.println("fuck in");
        try {
            token = token.replace("Bearer ", "");
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            try {
                ResponseEntity<User> userFromRest = restTemplate.getForEntity(USERSERVICE_URL + "/user/" + uid, User.class);
                if (userFromRest != null) {
                    Community communityInDatabase = communityRepository.findByCommunityNameIgnoreCase(community.getCommunityName());
                    if (communityInDatabase == null) {
                        community.setCommunityOwner(userFromRest.getBody());
                        Community savedCommunity = communityRepository.save(community);
                        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommunity);
                    } else {
                        HashMap<String, String> response = new HashMap<>();
                        response.put("resonse", "Community Name Already Exist");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            } catch (HttpStatusCodeException ex) {
                HashMap<String, String> response = new HashMap<>();
                response.put("resonse", "Not found this user");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(CommunityService.class.getName()).log(Level.SEVERE, null, ex);
            HashMap<String, String> response = new HashMap<>();
            response.put("resonse", "Your JWT Login Credential is Invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return null;
    }

    public ResponseEntity createPostToCommunity(String token, String communityId, Post newPostOfCommunity) {
        token = token.replace("Bearer ", "");
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            try {
                ResponseEntity<User> userFromRest = restTemplate.getForEntity(USERSERVICE_URL + "/user/" + uid, User.class);
                User userBody = userFromRest.getBody();
                if (userBody != null) {
                    Community communityInDatabase = communityRepository.findById(communityId).get();
                    if (communityInDatabase != null) {
                        Community community = communityRepository.findById(communityId).get();
                        newPostOfCommunity.setPostOfDate(new Date());
                        newPostOfCommunity.setUid(userBody.getUid());
                        newPostOfCommunity.setDisplayName(userBody.getDisplayName());
                        newPostOfCommunity.setPhotoURL(userBody.getPhotoURL());
                        newPostOfCommunity.setCommunityId(communityId);
                        community.getPostLists().add(newPostOfCommunity);
                        Post savedPost = postRepository.save(newPostOfCommunity);
                        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
                    } else {
                        HashMap<String, String> response = new HashMap<>();
                        response.put("resonse", "Not found community");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            } catch (HttpStatusCodeException ex) {
                HashMap<String, String> response = new HashMap<>();
                response.put("resonse", "Not found this user");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(CommunityService.class.getName()).log(Level.SEVERE, null, ex);
            HashMap<String, String> response = new HashMap<>();
            response.put("resonse", "Your JWT Login Credential is Invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return null;
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

    public ResponseEntity findCommunityById(String communityId, int page, int contentPerPage) {
        return ResponseEntity.status(HttpStatus.OK).body(communityRepository.findById(communityId).get());
    }

    public ResponseEntity getAllPostFromCommunity(String communityId, int page, int contentPerPage) {
        Page<Post> postListFromCommunity = postRepository.findByCommunityId(communityId, PageRequest.of(page, contentPerPage, Sort.Direction.DESC, "postDate"));
        return ResponseEntity.status(HttpStatus.OK).body(postListFromCommunity);
    }

    public ResponseEntity subscribeToCommunity(String token, String communityId) {
        token = token.replace("Bearer ", "");
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            try {
                Community communityInDatabase = communityRepository.findById(communityId).get();
                ResponseEntity<User> userFromRest = restTemplate.getForEntity(USERSERVICE_URL + "/user/" + uid, User.class);
                User userBody = userFromRest.getBody();
                if (userBody != null) {
                    if (communityInDatabase != null) {
                        UserCommunity userCommunity = new UserCommunity();
                        userCommunity.setUid(uid);
                        userCommunity.setCommunityId(communityId);
                        userCommunity.setDisplayName(userBody.getDisplayName());
                        userCommunity.setPhotoURL(userBody.getPhotoURL());
                        UserCommunity savedUserCommunity = userCommunityRepository.save(userCommunity);
                        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserCommunity);
                    } else {
                        HashMap<String, String> response = new HashMap<>();
                        response.put("resonse", "Not found community");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            } catch (HttpStatusCodeException ex) {
                HashMap<String, String> response = new HashMap<>();
                response.put("resonse", "Not found this user");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(CommunityService.class.getName()).log(Level.SEVERE, null, ex);
            HashMap<String, String> response = new HashMap<>();
            response.put("resonse", "Your JWT Login Credential is Invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return null;
    }
}
