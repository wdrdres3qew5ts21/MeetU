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
public class UserViewEvent extends User{
        
    private String elasticEventId;

    public String getElasticEventId() {
        return elasticEventId;
    }

    public void setElasticEventId(String elasticEventId) {
        this.elasticEventId = elasticEventId;
    }

    @Override
    public String toString() {
        return "UserViewEvent{" + "elasticEventId=" + elasticEventId + '}';
    }
    
}
