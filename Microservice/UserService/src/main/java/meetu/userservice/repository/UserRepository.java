/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.repository;

import java.util.List;
import meetu.userservice.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author wdrdr
 */
public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByFirstName(String firstName);
    
    public List<User> findByEmailLike(String emailKeyWord);
  
    public User findByUsername(String username);
    
    
}
