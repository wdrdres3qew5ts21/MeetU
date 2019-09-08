/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.repository;

import meetu.eventservice.model.Event;
import meetu.eventservice.model.EventTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Test
 */
@Repository
public interface EventTicketRespository extends MongoRepository<EventTicket, String> {
    
}
