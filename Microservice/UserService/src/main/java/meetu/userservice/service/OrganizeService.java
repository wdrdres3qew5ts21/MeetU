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
import meetu.userservice.model.User;
import meetu.userservice.model.UserOrganizeRole;
import meetu.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
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

    @Autowired
    private UserRepository userRepository;

    private static Logger log = LoggerFactory.getLogger(OrganizeService.class);

    public ResponseEntity createOrganize(String uid, Organize organize) {
        Organize organizeInDatabase = organizeRepository.findByOrganizeName(organize.getOrganizeName());
        User ownerDetail = userRepository.findByUid(uid);
        HashMap<String, Object> response = new HashMap<String, Object>();

        if (organizeInDatabase == null & ownerDetail != null) {
            System.out.println("---- Organize ---");
            System.out.println(organize);

            if (organize.getAdminEmailList().contains(ownerDetail.getEmail())) {
                response.put("response", "You already Owner so you can't add your email to be admin again !");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Admin organizeOwner = new Admin();
            organizeOwner.setUid(uid);
            organize.setOrganizeOwner(organizeOwner);
            List<String> adminEmailList = organize.getAdminEmailList();
            List<Admin> matchingAdminList = userRepository.findByEmailIsIn(adminEmailList);
            organize.setAdminList(matchingAdminList);

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
        } else if (organizeInDatabase == null & ownerDetail == null) {
            response.put("response", "Don't found any User in MeetU Applciation !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            System.out.println("organize name duplciate");
            response.put("response", "Organize Name Duplicate with existing name !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public List<Organize> findAllOrganizes(int page, int contentPerPage) {
        return organizeRepository.findAll(PageRequest.of(page, contentPerPage)).getContent();
    }

    public List<Organize> findByOrganizeName(String organizeName, int page, int contentPerPage) {
        return organizeRepository.findByOrganizeNameLike(organizeName, PageRequest.of(page, contentPerPage));
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

    public ResponseEntity findAllOrganizeOfUserIsIn(String uid, int page, int contentPerPage) {
        return new ResponseEntity(organizeRepository.findByOrganizeOwnerUidOrAdminListUidIsIn(uid, uid, PageRequest.of(page, contentPerPage)), HttpStatus.OK);
    }

    public ResponseEntity testFindAdminEmail(String uid, Organize organize) {
        List<Admin> findByEmailIsIn = userRepository.findByEmailIsIn(organize.getAdminEmailList());
        return ResponseEntity.status(HttpStatus.OK).body(findByEmailIsIn);
    }

    public ResponseEntity deleteAdminByUid(String organizeId, String adminUid) {
        Organize organize = organizeRepository.findById(organizeId).get();
        organize.getAdminList().removeIf(admin -> admin.getUid().equals(adminUid));
        organizeRepository.save(organize);
        return ResponseEntity.status(HttpStatus.OK).body(organize);
    }

    public ResponseEntity addAdminOrganize(String organizeId, String emailAdmin) {
        Organize organizeInDatabase = organizeRepository.findById(organizeId).get();
        HashMap<String, String> response = new HashMap<>();

        if (organizeInDatabase != null) {
            List<Admin> adminList = organizeInDatabase.getAdminList();
            User matchedUser = userRepository.findByEmailEquals(emailAdmin);
            // Optional<Admin> duplicateEmail = adminList.stream().filter(admin -> admin.getEmail().equals(emailAdmin)).findFirst();
            Admin duplicateAdminEmail = null;

            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).getEmail().equalsIgnoreCase(emailAdmin)) {
                    duplicateAdminEmail = adminList.get(i);
                }
            }
            if (duplicateAdminEmail == null) {
                Admin admin = new Admin();
                admin.setEmail(matchedUser.getEmail());
                admin.setUid(matchedUser.getUid());
                admin.setUsername(matchedUser.getUsername());
                admin.setDisplayName(matchedUser.getDisplayName());
                organizeInDatabase.getAdminList().add(admin);
                Organize savedOrganize = organizeRepository.save(organizeInDatabase);
                return ResponseEntity.status(HttpStatus.OK).body(savedOrganize);
            } else if (organizeInDatabase.getOrganizeOwner().getEmail().equals(emailAdmin)) {
                response.put("response", "Email already Exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                response.put("response", "You already Owner so you can't add your email to be admin again !");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        response.put("response", "Not found this Organize");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity updateOrganizeDetail(String organizeId, Organize organize) {
        String ownerUid = organize.getOrganizeOwner().getUid();
        Organize organizeOwnedByUser = organizeRepository.findByOrganizeOwnerUidAndOrganizeId(ownerUid, organizeId);
        if (organizeOwnedByUser != null) {
            organizeOwnedByUser.setEmail(organize.getEmail());
            organizeOwnedByUser.setInstragram(organize.getInstragram());
            organizeOwnedByUser.setLine(organize.getLine());
            organizeOwnedByUser.setOrganizeDetail(organize.getOrganizeDetail());
            organizeOwnedByUser.setOrganizeImageCover(organize.getOrganizeImageCover());
            organizeOwnedByUser.setOrganizeImageProfile(organize.getOrganizeImageProfile());
            organizeOwnedByUser.setPhone(organize.getPhone());
            organizeOwnedByUser.setTwitter(organize.getTwitter());
            organizeOwnedByUser.setWebsite(organize.getWebsite());
            Organize updatedOrganize = organizeRepository.save(organizeOwnedByUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedOrganize);
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("response", "You don't have permission to up date this organize");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
