/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.repository;

import java.util.List;
import meetu.userservice.model.UserOrganizeRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Test
 */
@Repository
public interface UserOrganizeRoleRepository extends MongoRepository<UserOrganizeRole, String>{
    
    public List<UserOrganizeRole> findAllByUid(String uid);
    
    
}
