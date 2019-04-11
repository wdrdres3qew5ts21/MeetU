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

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private Date birthDay;
    
    private String gender; 

    private String userImage;

    private List<Integer> interest = new ArrayList();

    private String province;

    private String country;

    private List<Badge> badgeList = new ArrayList<Badge>();

    public User() {

    }

    public User(String id, String username, String password, String role, String email, String firstName, String lastName, Date birthDay, String userImage, String province, String counry) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.userImage = userImage;
        this.province = province;
        this.country = country;
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
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    @Override
    public String toString() {
        return "User{" + "id=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDay=" + birthDay + ", gender=" + gender + ", userImage=" + userImage + ", interest=" + interest + ", province=" + province + ", country=" + country + ", badgeList=" + badgeList + '}';
    }
    
}
