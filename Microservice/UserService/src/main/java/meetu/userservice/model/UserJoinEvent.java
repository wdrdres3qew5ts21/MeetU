/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

/**
 *
 * @author Test
 */
public class UserJoinEvent extends UserViewEvent{
    
    private double exp;
    
    private boolean isJoinEvent;
    
    public UserJoinEvent() {
    }

    public UserJoinEvent(boolean isJoinEvent) {
        this.isJoinEvent = isJoinEvent;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }
    
    public boolean isIsJoinEvent() {
        return isJoinEvent;
    }

    public void setIsJoinEvent(boolean isJoinEvent) {
        this.isJoinEvent = isJoinEvent;
    }

    @Override
    public String toString() {
        return "UserJoinEvent{" + "isJoinEvent=" + isJoinEvent + '}';
    }
    
    
}
