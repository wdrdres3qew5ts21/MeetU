/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wdrdr
 */
public class User {

    private String userId;

    private String uid;

    private String email;

    private String username;
    
    private String displayName;
    
    private String photoURL;

    private String role;

    private String firstName;

    private String lastName;

    private Persona persona;

    private Date birthDay;

    private String gender;

    private String userImage;

    private String province;

    private String country;

    private List<String> interest = new ArrayList();

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    
    

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public User() {

    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public String getUserId() {
        return userId;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", uid=" + uid + ", email=" + email + ", username=" + username + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", persona=" + persona + ", birthDay=" + birthDay + ", gender=" + gender + ", userImage=" + userImage + ", province=" + province + ", country=" + country + '}';
    }

}
