/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @Indexed(direction = IndexDirection.ASCENDING)
    private String eventId;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String elasticEventId;

    private String eventName;

    private List<String> eventTags;

    private String eventDetail;

    private String eventPictureCover;

    private List<String> eventPictureLists;
    
    private List<String> userLists;

    private List<Post> postLists;
    
    private int totalView;
    
    private int numberOfTicket;
    
    private Date createEventDate;

    private Date endRegisterDate;

    private Date eventEndDate;

    private Date eventStartDate;
    
    private Location location;

    private Organize organize;
    
    private BadgeReward badge;

    public BadgeReward getBadge() {
        return badge;
    }

    public void setBadge(BadgeReward badge) {
        this.badge = badge;
    }
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<String> getUserLists() {
        if(userLists == null){
            userLists = new ArrayList<String>();
        }
        return userLists;
    }

    public void setUserLists(List<String> userLists) {
        this.userLists = userLists;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventTags() {
        if(eventTags == null){
            eventTags = new ArrayList<String>();
            return eventTags;
        }
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

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
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

    public List<Post> getPostLists() {
        return postLists;
    }

    public void setPostLists(List<Post> postLists) {
        this.postLists = postLists;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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

    public String getElasticEventId() {
        return elasticEventId;
    }

    public void setElasticEventId(String elasticEventId) {
        this.elasticEventId = elasticEventId;
    }

    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", elasticEventId=" + elasticEventId + ", eventName=" + eventName + ", eventTags=" + eventTags + ", eventDetail=" + eventDetail + ", eventPictureCover=" + eventPictureCover + ", eventPictureLists=" + eventPictureLists + ", userLists=" + userLists + ", postLists=" + postLists + ", totalView=" + totalView + ", numberOfTicket=" + numberOfTicket + ", createEventDate=" + createEventDate + ", endRegisterDate=" + endRegisterDate + ", eventEndDate=" + eventEndDate + ", eventStartDate=" + eventStartDate + ", location=" + location + ", organize=" + organize + '}';
    }

  
}
