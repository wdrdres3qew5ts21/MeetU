/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document("userOrganizeRoles")
public class UserOrganizeRole {
    
    @Id
    private String id;
    
    @Indexed
    private String organizeId;
    
    @Indexed
    private String uid;
    
    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }
    
    public List<String> getRoles() {
        if(roles == null){
            return roles = new ArrayList<String>();
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserOrganizeRole{" + "id=" + id + ", organizeId=" + organizeId + ", uid=" + uid + ", role=" + roles + '}';
    }
    
}
