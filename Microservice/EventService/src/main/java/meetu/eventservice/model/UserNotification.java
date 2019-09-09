/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document("userNotifications")
public class UserNotification {
    
    private String notificationToken;
    
    private String uid;

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserNotification{" + "notificationToken=" + notificationToken + ", uid=" + uid + '}';
    }
    
}
