/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document
public class UserBadge {
    
    @Id
    private String id;
    
    private String badgeId;
    
    private String badgeName;
    
    private int level;
    
    private double exp;
    
    private double expUntilUpToNextLevel;
    
    private Date unlockBadgeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getExpUntilUpToNextLevel() {
        return expUntilUpToNextLevel;
    }

    public void setExpUntilUpToNextLevel(double expUntilUpToNextLevel) {
        this.expUntilUpToNextLevel = expUntilUpToNextLevel;
    }

    public Date getUnlockBadgeDate() {
        return unlockBadgeDate;
    }

    public void setUnlockBadgeDate(Date unlockBadgeDate) {
        this.unlockBadgeDate = unlockBadgeDate;
    }

    @Override
    public String toString() {
        return "UserBadge{" + "badgeId=" + badgeId + ", badgeName=" + badgeName + ", level=" + level + ", exp=" + exp + ", expUntilUpToNextLevel=" + expUntilUpToNextLevel + ", unlockBadgeDate=" + unlockBadgeDate + '}';
    }

}
