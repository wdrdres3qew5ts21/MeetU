/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;


/**
 *
 * @author Test
 */
public class BadgeReward extends Badge{
    
    public BadgeReward() {
    }

    public BadgeReward(double exp) {
        this.exp = exp;
    }
    
    private double exp;

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }


    @Override
    public String toString() {
        return "BadgeReward{" + "exp=" + exp + '}';
    }
    
}
