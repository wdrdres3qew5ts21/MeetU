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
    
    private String notificationToken;
    
    private String uid;
    
    private String username;

    public String getToken() {
        return notificationToken;
    }

    public void setToken(String token) {
        this.notificationToken = token;
    }

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

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    @Override
    public String toString() {
        return "NotificationBody{" + "notificationToken=" + notificationToken + ", uid=" + uid + ", username=" + username + '}';
    }
    
}
