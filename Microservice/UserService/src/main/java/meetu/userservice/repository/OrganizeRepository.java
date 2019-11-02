/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.repository;

import java.util.List;
import meetu.userservice.model.Organize;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POPPULAR
 */
@Repository
public interface OrganizeRepository extends MongoRepository<Organize, String> {
    

    public Organize findByOrganizeName(String organizeName);

    public List<Organize> findByOrganizeOwnerUid(String uid);
    
    public Organize findByOrganizeOwnerUidAndOrganizeId(String uid, String organizeId);

    public List<Organize> findByOrganizeOwnerUidOrAdminListUidIsIn(String uidOwner, String uidAdmin, Pageable pageable);

    public void deleteByOrganizeIdAndAdminListUid(String uidAdmin);

    public List<Organize> findByOrganizeNameLike(String organizeName, Pageable pageable);
}
