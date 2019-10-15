/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.repository;

import java.util.List;
import meetu.eventservice.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wdrdr
 */
@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    public List<Event> findByEventDetailLike(String eventDetail);
    
    public Event findByElasticEventId(String elasticEventId);

    public void deleteByElasticEventId(String eventId);
    
     public List<Event> findByOrganizeOrganizeId(String organizeId); 

}
