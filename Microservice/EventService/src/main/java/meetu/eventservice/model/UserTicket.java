/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.sql.Timestamp;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "userTicket")
public class UserTicket {

    private String uid;

    private String eventElasticId;
    
    private String qrCodeSrc;

    private boolean isParticipate = false;

    private Timestamp participateDate;
    
    
    

}
