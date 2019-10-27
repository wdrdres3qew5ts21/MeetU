/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.repository;

import java.util.List;
import meetu.userservice.model.Badge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Test
 */
@Repository
public interface BadgeRepository extends MongoRepository<Badge, String>{
    
    public Badge findByBadgeNameEquals(String badgeName);
    
    public List<Badge> findByBadgeTagsIsIn(List<String> badgeTags, Pageable pageable);
    
    public List<Badge> findByBadgeNameLike(String badgeName, Pageable pageable);

    public List<Badge> findByBadgeTagsIsInAndBadgeNameLike(List<String> badgeTags,String badgeName, Pageable pageable);
    
}
