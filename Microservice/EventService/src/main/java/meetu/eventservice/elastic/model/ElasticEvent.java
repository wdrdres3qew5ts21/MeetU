/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.elastic.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author wdrdr
 */
public class ElasticEvent {
     
    private String id;
    
    private String eventName;
    
    private List<String> eventTags;
    
    private String eventDetail;
    
    private String location;
    
    private Date createEventDate;
    
    private Date endRegisterDate;
    
    private Date eventEndDate;
    
    private Date eventStartDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventTags() {
        return eventTags;
    }

    public void setEventTags(List<String> eventTags) {
        this.eventTags = eventTags;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateEventDate() {
        return createEventDate;
    }

    public void setCreateEventDate(Date createEventDate) {
        this.createEventDate = createEventDate;
    }

    public Date getEndRegisterDate() {
        return endRegisterDate;
    }

    public void setEndRegisterDate(Date endRegisterDate) {
        this.endRegisterDate = endRegisterDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    @Override
    public String toString() {
        return "ElasticEvent{" + "eventId=" + id + ", eventName=" + eventName + ", eventTags=" + eventTags + ", eventDetail=" + eventDetail + ", location=" + location + ", createEventDate=" + createEventDate + ", endRegisterDate=" + endRegisterDate + ", eventEndDate=" + eventEndDate + ", eventStartDate=" + eventStartDate + '}';
    }
    
}
