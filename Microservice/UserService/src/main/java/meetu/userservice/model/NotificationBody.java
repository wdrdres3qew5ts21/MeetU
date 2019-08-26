/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "notification")
public class NotificationBody {
    
    private String token;
    
    private String userId;
    
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "NotificationBody{" + "token=" + token + ", userId=" + userId + ", username=" + username + '}';
    }
    
    
    
}
