/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import java.util.List;
import meetu.userservice.user.Organize;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POPPULAR
 */
@Repository
public interface OrganizeRepository extends MongoRepository<Organize, String>{

    public List<Organize> findByOrganizeNameLike(String organizeName);
    
//    public List<User> findByFirstName(String firstName);
//    
//    public List<User> findByEmailLike(String emailKeyWord);
//  
//    public User findByUsername(String username);
    
    
}

