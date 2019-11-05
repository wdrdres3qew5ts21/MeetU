/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "userEventTicket")
public class UserEventTicket {

    @Id
    private String id;

    @Indexed
    private String ticketId;

    private String uid;

    private String eventName;

    private String elasticEventId;

    private String ticketKey;

    private String badgeId;

    private List<String> eventTags;

    private Date participateDate;

    private boolean isParticipate = false;

    private String deleteMessageDetail;

    private boolean isEventDelete = false;

    private Date eventEndDate;

    private Date eventStartDate;
    
    @Transient
    private String confirmDelete;
    
    @Transient
    private String organizeId;

    private Organize organize;
    
    private double exp;

    public String getConfirmDelete() {
        return confirmDelete;
    }

    public void setConfirmDelete(String confirmDelete) {
        this.confirmDelete = confirmDelete;
    }
    
    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }
    
    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }
    
    public Organize getOrganize() {
        return organize;
    }

    public void setOrganize(Organize organize) {
        this.organize = organize;
    }

    public boolean isIsEventDelete() {
        return isEventDelete;
    }

    public void setIsEventDelete(boolean isEventDelete) {
        this.isEventDelete = isEventDelete;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public String getDeleteMessageDetail() {
        return deleteMessageDetail;
    }

    public void setDeleteMessageDetail(String deleteMessageDetail) {
        this.deleteMessageDetail = deleteMessageDetail;
    }
    
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<String> getEventTags() {
        if (eventTags == null) {
            eventTags = new ArrayList<>();
        }
        return eventTags;
    }

    public void setEventTags(List<String> eventTags) {
        this.eventTags = eventTags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getElasticEventId() {
        return elasticEventId;
    }

    public void setElasticEventId(String elasticEventId) {
        this.elasticEventId = elasticEventId;
    }

    public String getTicketKey() {
        return ticketKey;
    }

    public void setTicketKey(String ticketKey) {
        this.ticketKey = ticketKey;
    }

    public boolean isIsParticipate() {
        return isParticipate;
    }

    public void setIsParticipate(boolean isParticipate) {
        this.isParticipate = isParticipate;
    }

    public Date getParticipateDate() {
        return participateDate;
    }

    public void setParticipateDate(Date participateDate) {
        this.participateDate = participateDate;
    }

    @Override
    public String toString() {
        return "UserEventTicket{" + "id=" + id + ", ticketId=" + ticketId + ", uid=" + uid + ", eventName=" + eventName + ", elasticEventId=" + elasticEventId + ", ticketKey=" + ticketKey + ", badgeId=" + badgeId + ", eventTags=" + eventTags + ", participateDate=" + participateDate + ", isParticipate=" + isParticipate + ", deleteMessageDetail=" + deleteMessageDetail + ", isEventDelete=" + isEventDelete + ", eventEndDate=" + eventEndDate + ", eventStartDate=" + eventStartDate + ", organize=" + organize + '}';
    }

}
