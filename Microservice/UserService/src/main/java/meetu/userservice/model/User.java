/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author wdrdr
 */
@Document(collection = "users")
public class User {

    @Id
    private String userId;

    @Indexed(unique = true)
    private String username;

    private String password;

    private String role;

    private String uid;

    @Email
    private String email;

    private boolean emailVerified;

    private String displayName;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String gender;

    private String photoURL;

    private List<Integer> interest = new ArrayList();

    private String province;

    private String country;
    
    private String phone;

    private List<Badge> badgeList = new ArrayList<Badge>();
    
    private String notificationToken;

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
        //this.password = passwordEncrypt.encode(password);
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDate;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDate = birthDay;
    }

    public String getUserImage() {
        return photoURL;
    }

    public void setUserImage(String userImage) {
        this.photoURL = userImage;
    }

    public List<Integer> getInterest() {
        return interest;
    }

    public void setInterest(List<Integer> interest) {
        this.interest = interest;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCounry(String counry) {
        this.country = counry;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Badge> getBadgeList() {
        return badgeList;
    }

    public void setBadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }
    

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", role=" + role + ", uid=" + uid + ", email=" + email + ", emailVerified=" + emailVerified + ", displayName=" + displayName + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDay=" + birthDate + ", gender=" + gender + ", photoURL=" + photoURL + ", interest=" + interest + ", province=" + province + ", country=" + country + ", phone=" + phone + ", badgeList=" + badgeList + '}';
    }

    
}
