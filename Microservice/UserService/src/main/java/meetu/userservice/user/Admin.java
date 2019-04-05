/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.user;

import java.util.List;

/**
 *
 * @author POPPULAR
 */
public class Admin extends User {
    private List<String>  roles ;

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
