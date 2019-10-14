/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.List;

/**
 *
 * @author Test
 */
public class RoleList {
    
    private List<UserOrganizeRole> roles;

    public List<UserOrganizeRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserOrganizeRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RoleList{" + "roles=" + roles + '}';
    }
    
}
