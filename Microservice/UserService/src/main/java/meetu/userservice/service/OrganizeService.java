/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import meetu.userservice.repository.OrganizeRepository;
import meetu.userservice.repository.UserCommunityRoleRepository;
import meetu.userservice.repository.UserOrganizeRoleRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import meetu.userservice.model.Admin;
import meetu.userservice.model.Organize;
import meetu.userservice.model.UserOrganizeRole;
import meetu.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private UserOrganizeRoleRepository userOrganizeRoleRepository;

    @Autowired
    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(OrganizeService.class);

    public ResponseEntity createOrganize(String uid, Organize organize) {
        Organize organizeInDatabase = organizeRepository.findByOrganizeName(organize.getOrganizeName());
        if (organizeInDatabase == null) {
            Admin organizeOwner = new Admin();
            organizeOwner.setUid(uid);
            organize.setOrganizeOwner(organizeOwner);

            Organize savedOrganize = organizeRepository.save(organize);
            UserOrganizeRole userOrganizeRole = new UserOrganizeRole();
            userOrganizeRole.setOrganizeId(savedOrganize.getOrganizeId());
            userOrganizeRole.setUid(uid);
            ArrayList<String> roles = new ArrayList();
            roles.add("owner");
            userOrganizeRole.setRoles(roles);
            userOrganizeRoleRepository.save(userOrganizeRole);

            userService.updateUserOrganizeRoleClaim(uid);
            System.out.println(savedOrganize);
            return ResponseEntity.status(HttpStatus.CREATED).body(organizeRepository.save(savedOrganize));
        } else {
            System.out.println("organize name duplciate");
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("response", "Organize Name Duplicate with existing name !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
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
        Organize organizeInDatabase = organizeRepository.findById(organizeId).get();
        if (organizeInDatabase != null) {
            System.out.println("----------- Found organize -------");
            System.out.println(organizeInDatabase);
            return new ResponseEntity(organizeInDatabase, HttpStatus.OK);
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("response", "Not found Any Organize");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    public ResponseEntity findAllOrganizeOfUser(String uid) {
        return new ResponseEntity(organizeRepository.findByOrganizeOwnerUid(uid), HttpStatus.OK);
    }

}
