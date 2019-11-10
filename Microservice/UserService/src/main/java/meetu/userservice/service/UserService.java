/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.JsonArray;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.userservice.filters.TokenAuthenticationService;
import meetu.userservice.model.Badge;
import meetu.userservice.model.InterestGenreBehavior;
import meetu.userservice.model.Persona;
import meetu.userservice.repository.UserRepository;
import meetu.userservice.model.User;
import meetu.userservice.model.UserBadge;
import meetu.userservice.model.UserJoinEvent;
import meetu.userservice.model.UserNotification;
import meetu.userservice.model.UserViewEvent;
import meetu.userservice.repository.BadgeRepository;
import meetu.userservice.repository.OrganizeRepository;
import meetu.userservice.repository.UserBadgeRepository;
import meetu.userservice.repository.UserNotificationRepository;
import meetu.userservice.repository.UserOrganizeRoleRepository;
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
    private OrganizeRepository organizeRepository;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private UserOrganizeRoleRepository userOrganizeRoleRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private BadgeRepository badgeRepository;

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

    public ResponseEntity findByUId(String uid) {
        User userInDatabase = userRepository.findByUid(uid);
        if (userInDatabase != null) {
            System.out.println("Foundd user");
            System.out.println(userInDatabase);
            return ResponseEntity.status(HttpStatus.OK).body(userInDatabase);
        }

        HashMap<String, String> response = new HashMap();
        response.put("message", "not found user in database!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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

    public ResponseEntity saveNotificationToken(UserNotification notificationBody) {
        System.out.println("---- ------------------------");
        System.out.println("Notification Token //" + notificationBody.getUid() + " /// token: " + notificationBody.getNotificationToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(userNotificationRepository.save(notificationBody));
    }

    public ResponseEntity userViewEvent(UserViewEvent userViewEvent) {
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
            System.out.println("-------------");
            userRepository.save(userInDatabase);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInDatabase);
        }
        HashMap<String, String> responseBody = new HashMap();
        responseBody.put("response", "Not found any user in database !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public ResponseEntity updateUserInterestPersonaFromJoinEvent(UserJoinEvent userJoinEvent) {
        User userInDatabase = userRepository.findByUid(userJoinEvent.getUid());
        if (userInDatabase != null) {
            System.out.println("found user !!!");
            System.out.println(userInDatabase.getUid());
            System.out.println(userInDatabase);
            Persona userPersona = userInDatabase.getPersona();
            List<InterestGenreBehavior> interestBehaviorList = userPersona.getInterestBehaviorList();
            System.out.println("Before Update Interest Behavior");
            System.out.println(interestBehaviorList.toString());
            // เริ่มอัพเดท persona ใให้ตรงกับ tag ของกิจกรรมที่ส่งมา
            interestBehaviorList.forEach(interestBehavior -> {
                String genre = interestBehavior.getGenre();
                if (userJoinEvent.getEventTags().contains(genre) == true) {
                    int totalView = interestBehavior.getTotalView();
                    int totalParticipate = interestBehavior.getTotalParticipate();
                    totalView++;
                    totalParticipate++;
                    interestBehavior.setTotalView(totalView);
                    interestBehavior.setTotalParticipate(totalParticipate);
                }
            });
            System.out.println("After Update Interest Behavior");
            System.out.println(interestBehaviorList.toString());
            System.out.println("-------------");
            // ทำการเพิ่ม EXP ให้กับ Badge นั้นๆและถ้ายังไม่เคยมี Badge ให้ทำการสร้าง Badge ใหม่เข้าไป
            Badge eventBadge = badgeRepository.findById(userJoinEvent.getBadgeId()).get();
            List<UserBadge> userBadgeList = userInDatabase.getBadgeList();
            System.out.println("DSFDSFSDF");

            if (userBadgeList.size() == 0) {
                // ไม่เคยมี Badge เลยให้ทำการ push ลงไปเลย
                System.out.println("Initial New Badge from scratch");
                UserBadge userBadge = new UserBadge();
                userBadge.setBadgeId(eventBadge.getBadgeId());
                userBadge.setBadgeName(eventBadge.getBadgeName());
                userBadge.setBadgePicture(eventBadge.getBadgePicture());
                userBadge.setExp(userJoinEvent.getExp());
                userBadge.setExpUntilUpToNextLevel(300);
                userBadge.setUnlockBadgeDate(new Date());
                userBadge.setUid(userInDatabase.getUid());
                userBadgeList.add(userBadge);
                // ต้องบันทึก Badge นี้ลง Table Ranking ที่เห็นทุกๆ Badge 

            } else {
                // เคยมี Badge อยู่แล้วแสดงว่าต้องเพิ่มคะแนนราย Badge แล้วก็เช็คว่าถึงเวลาอัพเลเวลไหม
                System.out.println("Own badge at least 1");
                System.out.println("!!!!!!!!!!!!!!!");
                System.out.println(userBadgeList);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                UserBadge matchingBadgeForUpExp = userBadgeList.stream()
                        .filter(ownedBadge -> ownedBadge.getBadgeId().equals(eventBadge.getBadgeId()))
                        .findFirst()
                        .orElse(new UserBadge());
                System.out.println("Found Matching !");
                System.out.println(matchingBadgeForUpExp);
                if (matchingBadgeForUpExp.getBadgeId() == null) {
                    // ไม่เคยครอบครอง Badge ชนิดนี้มาก่อนเลย ต้องทำการเพิม่ลงไปในระบบ 
                    System.out.println("Add new badge to List< badge>");
                    UserBadge userBadge = new UserBadge();
                    userBadge.setBadgeId(eventBadge.getBadgeId());
                    userBadge.setBadgeName(eventBadge.getBadgeName());
                    userBadge.setBadgePicture(eventBadge.getBadgePicture());
                    userBadge.setExp(userJoinEvent.getExp());
                    userBadge.setExpUntilUpToNextLevel(300);
                    userBadge.setUnlockBadgeDate(new Date());
                    userBadge.setUid(userInDatabase.getUid());
                    userBadgeList.add(userBadge);
                } else {
                    double currentExp = matchingBadgeForUpExp.getExp();
                    currentExp += userJoinEvent.getExp();
                    if (currentExp >= matchingBadgeForUpExp.getExpUntilUpToNextLevel()) {
                        int currentLevel = matchingBadgeForUpExp.getLevel();
                        currentLevel++;
                        double expAfterLevelUp = currentExp - matchingBadgeForUpExp.getExpUntilUpToNextLevel();
                        matchingBadgeForUpExp.setExp(expAfterLevelUp);
                        matchingBadgeForUpExp.setLevel(currentLevel);
                        double expUntilUpToNextLevel = matchingBadgeForUpExp.getExpUntilUpToNextLevel();
                        expUntilUpToNextLevel = 1.2 * expUntilUpToNextLevel;
                        matchingBadgeForUpExp.setExpUntilUpToNextLevel(expUntilUpToNextLevel);
                        System.out.println("Up EXP !!!");
                        System.out.println(matchingBadgeForUpExp);
                    } else {
                        matchingBadgeForUpExp.setExp(currentExp);
                    }
                    matchingBadgeForUpExp.setUid(userInDatabase.getUid());
                    System.out.println("------ fin stream update exp level -----");
                    System.out.println(matchingBadgeForUpExp);
                    System.out.println(userBadgeList);
                }
            }
            userBadgeRepository.saveAll(userBadgeList);
            userRepository.save(userInDatabase);
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

    public ResponseEntity editUserProfile(String uid, User editedUserProfile) {
        User userInDatabase = userRepository.findByUid(uid);
        if (userInDatabase != null) {
            userInDatabase.setEmail(editedUserProfile.getEmail());
            userInDatabase.setFirstName(editedUserProfile.getFirstName());
            userInDatabase.setLastName(editedUserProfile.getLastName());
            userInDatabase.setPhone(editedUserProfile.getPhone());
            userInDatabase.setGender(editedUserProfile.getGender());
            userInDatabase.setCounry(editedUserProfile.getCountry());
            userInDatabase.setBirthDate(editedUserProfile.getBirthDate());
            userInDatabase.setWebsite(editedUserProfile.getWebsite());
            userInDatabase.setLine(editedUserProfile.getLine());
            userInDatabase.setFacebook(editedUserProfile.getFacebook());
            userInDatabase.setTwitter(editedUserProfile.getTwitter());
            userInDatabase.setInstagram(editedUserProfile.getInstagram());
            User savedUserProfile = userRepository.save(userInDatabase);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserProfile);
        }
        HashMap<String, String> message = new HashMap();
        message.put("response", "Cannt update profile");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public ResponseEntity updateUserPreference(User updatedUserInterest) {
        System.out.println("Fuck update");
        User userInDatabase = userRepository.findByUid(updatedUserInterest.getUid());
        if (userInDatabase != null) {
            userInDatabase.setInterest(updatedUserInterest.getInterest());
            User savedUserIntest = userRepository.save(userInDatabase);
            if (savedUserIntest != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(savedUserIntest);
            }
        }
        HashMap<String, String> message = new HashMap<>();
        message.put("response", "Failed to update user interest !");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public ResponseEntity updateUserOrganizeRoleClaim(String uid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("isOrganize", true);
//        List<UserOrganizeRole> userOrganizeRole = userOrganizeRoleRepository.findAllByUid(uid);
//        System.out.println(userOrganizeRole);
//        User userFromDatabase = userRepository.findByUid(uid);
//        System.out.println(userFromDatabase);
//        RoleList roleList = new RoleList();
//        roleList.setRoles(userOrganizeRole);
//        ObjectMapper object = new ObjectMapper();
//        Map<String, Object> roleMap = object.convertValue(roleList, Map.class);
        try {
            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
            System.out.println("!!! Update Claims");
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResponseEntity updateUserCommunityRoleClaim(String uid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("isCommunity", true);
        try {
            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
            System.out.println("!!! Update Claims");
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResponseEntity findUserByEmail(String email) {
        User userFromDatabase = userRepository.findByEmailEquals(email);
        System.out.println(userFromDatabase);
        if (userFromDatabase != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userFromDatabase);
        }
        HashMap<String,String> response = new HashMap<>();
        response.put("response", "Not found This "+email+" email !!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
