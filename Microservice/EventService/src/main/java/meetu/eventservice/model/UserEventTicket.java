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
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "userEventTicket")
public class UserEventTicket {

    @Id
    private String id;

    private String uid;

    private String elasticEventId;

    private String ticketKey;
    
    private List<String> eventTags;

    private boolean isParticipate = false;

    private Date participateDate;

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
        return "UserEventTicket{" + "id=" + id + ", uid=" + uid + ", eventElasticId=" + elasticEventId + ", ticketKey=" + ticketKey + ", isParticipate=" + isParticipate + ", participateDate=" + participateDate + '}';
    }

}
