/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author POPPULAR
 */
public class Admin {
    
    private String uid;

    private String username;
    
    private List<String>  roles ;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString()+"Admin{" + "roles=" + roles + '}';
    }
    
}
