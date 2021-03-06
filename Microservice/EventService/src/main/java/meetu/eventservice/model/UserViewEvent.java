/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.List;

/**
 *
 * @author Test
 */
public class UserViewEvent extends User{
        
    private String elasticEventId;
    
    private List<String> eventTags;

    public List<String> getEventTags() {
        return eventTags;
    }

    public void setEventTags(List<String> eventTags) {
        this.eventTags = eventTags;
    }
    
    public String getElasticEventId() {
        return elasticEventId;
    }

    public void setElasticEventId(String elasticEventId) {
        this.elasticEventId = elasticEventId;
    }

    @Override
    public String toString() {
        return "UserViewEvent{" + "elasticEventId=" + elasticEventId + ", eventTags=" + eventTags + '}';
    }

}
