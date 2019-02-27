/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "events")
public class Event {
    
    @Id
    @Indexed(direction = IndexDirection.ASCENDING)
     private String eventId;
    
    private String eventName;
    
    private List<String> eventTags;
    
    private String eventDetail;
    
    private String eventPictureCover;
    
    private List<String> eventPictureLists;
    
    private String location;
    
    private List<Post> postLists;
    
    private Date createEventDate;
    
    private Date endRegisterDate;
    
    private Date eventEndDate;
    
    private Date eventStartDate;
    
    private Organize organize;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getEventPictureLists() {
        return eventPictureLists;
    }

    public void setEventPictureLists(List<String> eventPictureLists) {
        this.eventPictureLists = eventPictureLists;
    }

    public String getEventPictureCover() {
        return eventPictureCover;
    }

    public void setEventPictureCover(String eventPictureCover) {
        this.eventPictureCover = eventPictureCover;
    }
    
    public Organize getOrganize() {
        return organize;
    }

    public void setOrganize(Organize organize) {
        this.organize = organize;
    }

    
}
