/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import org.springframework.data.annotation.Id;
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
    private String id;

    private String username;

    private String password;

    private String role;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String userImage;

    private List<Integer> interest = new ArrayList();

    private String province;

    private String counry;

    public User() {

    }

    public User(String id, String username, String password, String role, String email, String firstName, String lastName, Date birthDay, String userImage, String province, String counry) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.userImage = userImage;
        this.province = province;
        this.counry = counry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.password = passwordEncrypt.encode(password);
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

    public String getCounry() {
        return counry;
    }

    public void setCounry(String counry) {
        this.counry = counry;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDay=" + birthDay + ", userImage=" + userImage + ", interest=" + interest + ", province=" + province + ", counry=" + counry + '}';
    }

}
