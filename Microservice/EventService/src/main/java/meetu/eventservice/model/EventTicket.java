/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "eventTickets")
public class EventTicket {

    private String uid;

    private String eventElasticId;

    private boolean isParticipate = false;
    
    private Timestamp participateDate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEventElasticId() {
        return eventElasticId;
    }

    public void setEventElasticId(String eventElasticId) {
        this.eventElasticId = eventElasticId;
    }

    public boolean isIsParticipate() {
        return isParticipate;
    }

    public void setIsParticipate(boolean isParticipate) {
        this.isParticipate = isParticipate;
    }

    public Timestamp getParticipateDate() {
        return participateDate;
    }

    public void setParticipateDate(Timestamp participateDate) {
        this.participateDate = participateDate;
    }

    @Override
    public String toString() {
        return "EventTicket{" + "uid=" + uid + ", eventElasticId=" + eventElasticId + ", isParticipate=" + isParticipate + ", participateDate=" + participateDate + '}';
    }


}
