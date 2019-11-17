/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.repository;

import meetu.eventservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Test
 */
public interface ReviewRepository extends MongoRepository<Review, String>{

    public Review findByUidAndElasticEventId(String uid, String elasticEventId);
    
}
