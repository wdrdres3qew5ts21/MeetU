/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
public class UserBadge {
    
    private int id;
    
    private String badge;
    
    private int level;
    
    private int exp;
    
    private double expUntilUpToNextLevel;
    
    private Date unlockBadgeDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
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
        return "Badge{" + "id=" + id + ", badge=" + badge + ", level=" + level + ", exp=" + exp + ", expUntilUpToNextLevel=" + expUntilUpToNextLevel + ", unlockBadgeDate=" + unlockBadgeDate + '}';
    }
    
}
