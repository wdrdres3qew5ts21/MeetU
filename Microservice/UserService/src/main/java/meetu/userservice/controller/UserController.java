/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import meetu.userservice.model.NotificationBody;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.userservice.service.UserService;
import meetu.userservice.model.Badge;
import meetu.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wdrdr
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    //@Value("${server.message.greeting}")
    private String testMessage;

    @GetMapping("/test")
    public ResponseEntity<String> getTestMessage() {
        return new ResponseEntity<String>(testMessage, HttpStatus.OK);
    }

    @PostMapping("/user/jwt")
    public ResponseEntity<HashMap<String, Object>> verifyJwtToken(@RequestBody HashMap<String, Object> jwtRequestBody) {
        //return new ResponseEntity<HashMap<String, Object>>(this.userService.verifyJwtToken(jwtRequestBody),HttpStatus.OK);
        return this.userService.verifyJwtToken(jwtRequestBody);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String email, @RequestParam(required = false) String firstName) {
        if (email != null) {
            return new ResponseEntity<List<User>>(userService.findByEmailLike(email), HttpStatus.OK);
        } else if (firstName != null) {
            return new ResponseEntity<List<User>>(userService.findByFirstName(firstName), HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return new ResponseEntity<String>(userService.login(user), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<User> giveExpFromEventToBadge(@RequestBody HashMap<String, Object> expForBadge) {
        int expFromEvent = (int) (expForBadge.get("expFromEvent"));
        String badgeId = (String) (expForBadge.get("badgeId"));
        String userId = (String) (expForBadge.get("userId"));
        //userService.giveExpFromEventToBadge();
        return null;
    }

    @PostMapping("/notification/token")
    public User saveNotificationToken(@RequestBody NotificationBody notificationBody) {
        return null;
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
