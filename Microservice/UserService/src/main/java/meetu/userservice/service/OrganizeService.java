/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import meetu.userservice.repository.OrganizeRepository;
import java.util.ArrayList;
import java.util.List;
import meetu.userservice.model.Admin;
import meetu.userservice.model.Organize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author POPPULAR
 */
@Service
public class OrganizeService {

    @Autowired
    private OrganizeRepository organizeRepository;

    public Organize createOrganize(String uid, Organize organize) {
        Admin organizeOwner = new Admin();
        organizeOwner.setUid(uid);
        organize.setOrganizeOwner(organizeOwner);
        organize.getAdminList();
        return organizeRepository.save(organize);
    }

    public Organize addAdminOrganize(Admin[] adminList, Organize organize) {
        return null;
    }

    public List<Organize> findAllOrganizes() {
        return organizeRepository.findAll();
    }

    public List<Organize> findByOrganizeName(String organizeName) {
        return organizeRepository.findByOrganizeNameLike(organizeName);
    }

    public ResponseEntity findOrganizeById(String organizeId) {
        return ResponseEntity.status(HttpStatus.OK).body(organizeRepository.findById(organizeId));
        
    }

}
