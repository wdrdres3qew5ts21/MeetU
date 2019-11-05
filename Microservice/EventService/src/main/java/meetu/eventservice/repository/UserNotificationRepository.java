/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.repository;

import java.util.List;
import meetu.eventservice.model.UserNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Test
 */
public interface UserNotificationRepository extends MongoRepository<UserNotification, String> {
    
    public UserNotification findByUid(String uid);

    public List<UserNotification> findByUidIsIn(List<String> userList);

    
}
