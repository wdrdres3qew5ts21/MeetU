/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import java.util.HashMap;
import java.util.List;
import meetu.userservice.model.Badge;
import meetu.userservice.model.UserBadge;
import meetu.userservice.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Test
 */
@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public ResponseEntity createBadge(Badge badge) {
        Badge badgeInDatabase = badgeRepository.findByBadgeNameEquals(badge.getBadgeName());
        if (badgeInDatabase == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(badgeRepository.save(badge));
        }
        HashMap<String, String> response = new HashMap();
        response.put("response", "Badge Name Duplicate !!!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity findBadgeByFilter(List<String> badgeTags, String badgeName, int page, int contentPerPage) {
        if (badgeTags == null & (badgeName.isEmpty() | badgeName == null)) {
            List<Badge> allBadge = badgeRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allBadge);
        } else if (badgeTags != null & !badgeName.isEmpty()) {
            List<Badge> badgeFilterByTagsAndName = badgeRepository.findByBadgeTagsIsInAndBadgeNameLike(badgeTags, badgeName, PageRequest.of(page, contentPerPage));
            return ResponseEntity.status(HttpStatus.OK).body(badgeFilterByTagsAndName);
        }
        System.out.println("Filter some bade");
        System.out.println(badgeTags);
        List<Badge> matchingBadge = badgeRepository.findByBadgeTagsIsIn(badgeTags, PageRequest.of(page, contentPerPage));
        return ResponseEntity.status(HttpStatus.OK).body(matchingBadge);
    }

    public ResponseEntity findBadgeById(String badgeId) {
        Badge badgeInDatabase = badgeRepository.findById(badgeId).get();
        HashMap<String, String> response = new HashMap();
        if (badgeInDatabase != null) {
            return ResponseEntity.status(HttpStatus.OK).body(badgeInDatabase);

        }
        response.put("response", "Fail to create Event Because Badge ID Not found : ");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
