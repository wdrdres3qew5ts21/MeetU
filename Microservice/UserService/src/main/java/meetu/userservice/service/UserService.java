/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.userservice.filters.TokenAuthenticationService;
import meetu.userservice.model.InterestGenreBehavior;
import meetu.userservice.model.Persona;
import meetu.userservice.repository.UserRepository;
import meetu.userservice.model.User;
import meetu.userservice.model.UserViewEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public User createUserFromFirebaase(User user) {
        System.out.println("----- Create User/ Update User -------");
        System.out.println(user);
        user.setUsername(user.getUid());
//        BCryptPasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
//        user.setPassword(passwordEncrypt.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> findByEmailLike(String email) {
        return userRepository.findByEmailLike(email);
    }

    public boolean isUserMoreThanOne(List<User> users) {
        return users.size() > 1 ? true : false;
    }

    public String verifyJwtToken() {
        return null;
    }

    public String login(User userFromInput) {
        User userFromDatabase = userRepository.findByUsername(userFromInput.getUsername());
        String usernameFromInput = userFromInput.getUsername();
        String passwordFromInput = userFromInput.getPassword();
        String usernameFromDatabase = userFromDatabase.getUsername();
        BCryptPasswordEncoder passwordDecrypt = new BCryptPasswordEncoder();
        String passwordFromDatabase = userFromDatabase.getPassword();
        boolean isPasswordCorrectFromEncryptDatabase = passwordDecrypt.matches(passwordFromInput, passwordFromDatabase);

        if (usernameFromInput.equals((usernameFromDatabase)) && isPasswordCorrectFromEncryptDatabase) {
            return tokenAuthenticationService.createTokenUser(userFromDatabase);
        } else {
            return "Sorry Login fail!!!!!!!";
        }
    }

    public ResponseEntity verifyJwtToken(HashMap<String, Object> jwtRequestBody) {
        try {
            System.out.println("Test JWT Token");
            System.out.println(jwtRequestBody);
            String token = jwtRequestBody.get("token").toString();
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Decode UID : " + uid);
            return ResponseEntity.status(HttpStatus.OK).body("success full verify with JWT Token");
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User saveNotificationToken(User notificationBody) {
        System.out.println("---- ------------------------");
        System.out.println("Notification Token //" + notificationBody.getUid() + " /// token: " + notificationBody.getNotificationToken());

        return null;
    }

    public ResponseEntity updateUserInterestPersona(UserViewEvent userViewEvent) {
        User userInDatabase = userRepository.findByUid(userViewEvent.getUid());
        if (userInDatabase != null) {
            System.out.println("found user !!!");
            System.out.println(userInDatabase.getUid());
            System.out.println(userInDatabase);
            Persona userPersona = userInDatabase.getPersona();
            List<InterestGenreBehavior> interestBehaviorList = userPersona.getInterestBehaviorList();
            System.out.println("Before Update Interest Behavior");
            System.out.println(interestBehaviorList.toString());
            interestBehaviorList.forEach(interestBehavior -> {
                String genre = interestBehavior.getGenre();
                if (userViewEvent.getEventTags().contains(genre) == true) {
                    int totalView = interestBehavior.getTotalView();
                    totalView++;
                    interestBehavior.setTotalView(totalView);
                }
            });
            System.out.println("After Update Interest Behavior");
            System.out.println(interestBehaviorList.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(userInDatabase);
        }
        HashMap<String, String> responseBody = new HashMap();
        responseBody.put("response", "Not found any user in database !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public ResponseEntity createCustomToken() {
        try {
            String uid = "some-uid";
            Map<String, Object> additionalClaims = new HashMap<String, Object>();
            additionalClaims.put("premiumAccount", true);

            String customToken = FirebaseAuth.getInstance()
                    .createCustomToken(uid, additionalClaims);

            return ResponseEntity.status(HttpStatus.CREATED).body(customToken);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
