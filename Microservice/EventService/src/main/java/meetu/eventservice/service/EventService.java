/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import java.util.Date;
import java.util.List;
import meetu.eventservice.model.Event;
import meetu.eventservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    public Event createEvent(Event event) {
        event.setCreateEventDate(new Date());
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }
    
}
