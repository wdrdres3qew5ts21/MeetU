/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.controller;

import java.util.List;
import meetu.userservice.model.Admin;
import meetu.userservice.model.Organize;
import meetu.userservice.service.OrganizeService;
import meetu.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author POPPULAR
 */
@CrossOrigin(origins = "*")
@RestController
public class OrganizeController {

    @Autowired
    private OrganizeService organizeService;

    @PostMapping("/organize/{uid}")
    public ResponseEntity createOrganize(@PathVariable String uid, @RequestBody Organize organize) {
        return organizeService.createOrganize(uid, organize);
    }

    @PatchMapping("/organize/{organizeId}")
    public ResponseEntity updateOrganizeDetail(@PathVariable String organizeId, @RequestBody Organize organize) {
        return organizeService.updateOrganizeDetail(organizeId, organize);
    }

    @PostMapping("/test/organize/{uid}")
    public ResponseEntity testFindAdminEmail(@PathVariable String uid, @RequestBody Organize organize) {
        return organizeService.testFindAdminEmail(uid, organize);
    }

    @DeleteMapping("/organize/{organizeId}/{uid}")
    public ResponseEntity deleteAdminByUid(@PathVariable String organizeId, @PathVariable String uid) {
        return organizeService.deleteAdminByUid(organizeId, uid);
    }

    @GetMapping("/organize/{organizeId}/admin/status")
    public ResponseEntity verifyIfUserIsOrganizeMember(
            @RequestHeader(required = true, name = "Authorization") String token,
            @PathVariable String organizeId) {
        return organizeService.verifyIfUserIsOrganizeMember(token, organizeId);
    }

    @GetMapping("/organize/user/{uid}")
    public ResponseEntity findAllOrganizeOfUser(
            @PathVariable String uid,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage) {
        return organizeService.findAllOrganizeOfUserIsIn(uid, page, contentPerPage);
    }

    @GetMapping("/organize/{organizeId}/admins")
    public ResponseEntity findAdminInOrganize(
            @PathVariable String organizeId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage) {
        return organizeService.findAdminInOrganize(organizeId, page, contentPerPage);
    }

    @PostMapping("/organize/{organizeId}/admin/{email}")
    public ResponseEntity addAdminOrganize(@PathVariable String organizeId, @PathVariable String email) {
        return organizeService.addAdminOrganize(organizeId, email);
    }

    @GetMapping("/organizes")
    public ResponseEntity findAllOrganizes(
            @RequestParam(required = false) String organizeName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage) {
        if (organizeName == null) {
            return new ResponseEntity<List<Organize>>(organizeService.findAllOrganizes(page, contentPerPage), HttpStatus.OK);
        }
        return new ResponseEntity<List<Organize>>(organizeService.findByOrganizeName(organizeName, page, contentPerPage), HttpStatus.OK);
    }

    @GetMapping("/organize/{organizeId}")
    public ResponseEntity findOrganizeById(@PathVariable String organizeId) {
        return organizeService.findOrganizeById(organizeId);
    }

}
