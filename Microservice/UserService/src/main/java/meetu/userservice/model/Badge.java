/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document("badges")
public class Badge {
    
    @Id
    private String badgeId;
    
    private String badgeName;
    
    private String badgePicture;
    
    private List<String> badgeTags;
   

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public List<String> getBadgeTags() {
        return badgeTags;
    }

    public void setBadgeTags(List<String> badgeTags) {
        this.badgeTags = badgeTags;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgePicture() {
        return badgePicture;
    }

    public void setBadgePicture(String badgePicture) {
        this.badgePicture = badgePicture;
    }

    @Override
    public String toString() {
        return "Badge{" + "badgeId=" + badgeId + ", badgeName=" + badgeName + ", badgePicture=" + badgePicture + ", badgeTags=" + badgeTags  + '}';
    }
    
}
