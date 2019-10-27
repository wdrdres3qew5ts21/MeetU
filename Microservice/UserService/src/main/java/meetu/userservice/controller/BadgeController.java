/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.controller;

import meetu.userservice.service.BadgeService;
import java.util.List;
import meetu.userservice.model.Badge;
import meetu.userservice.model.UserBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Test
 */
@RestController
@CrossOrigin(origins = "*")
public class BadgeController {
    
    @Autowired
    BadgeService badgeService;
    
    @GetMapping("/badge/{badgeId}")
    public ResponseEntity findBadgeById(@PathVariable String badgeId) {
        return badgeService.findBadgeById(badgeId);
    }
    
    @PostMapping("/badge")
    public ResponseEntity createBadge(@RequestBody Badge badge) {
        return badgeService.createBadge(badge);
    }
    
    @GetMapping("/badges")
    public ResponseEntity findBadgeByFilter(
            @RequestParam(required = false) List<String> badgeTags,
            @RequestParam(required = false, defaultValue = "") String badgeName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage ) {
        return badgeService.findBadgeByFilter(badgeTags, badgeName,page,contentPerPage);
    }
    
    @GetMapping("/ranking")
    public ResponseEntity findRankingOfUserInBadge(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage ) {
        return badgeService.findRankingOfUserInBadge(page,contentPerPage);
    }
    
}
