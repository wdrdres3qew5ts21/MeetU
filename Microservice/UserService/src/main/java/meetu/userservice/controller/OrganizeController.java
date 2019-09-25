/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.controller;

import java.util.List;
import meetu.userservice.model.Admin;
import meetu.userservice.service.OrganizeService;
import meetu.userservice.model.Organize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author POPPULAR
 */
@RestController
public class OrganizeController {

    @Autowired
    private OrganizeService organizeService;

    @PostMapping("/organize/{userId}")
    public ResponseEntity<Organize> createOrganize(@PathVariable String userId, @RequestBody Organize organize) {
        System.out.println("fuq !");
        return new ResponseEntity<Organize>(organizeService.createOrganize(userId, organize), HttpStatus.CREATED);
    }
    
    public ResponseEntity addAdminOrganize(@PathVariable Admin adminList[], @RequestBody Organize organize) {
        return new ResponseEntity<Organize>(organizeService.addAdminOrganize(adminList, organize), HttpStatus.CREATED);
    }

    @GetMapping("/organizes")
    public ResponseEntity findAllOrganizes(@RequestParam(required = false) String organizeName) {
        if (organizeName == null) {
            return new ResponseEntity<List<Organize>>(organizeService.findAllOrganizes(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Organize>>(organizeService.findByOrganizeName(organizeName), HttpStatus.OK);
    }
    
    @GetMapping("/organize/{organizeId}")
    public ResponseEntity findOrganizeById(@PathVariable String organizeId) {
        return new ResponseEntity(organizeService.findOrganizeById(organizeId), HttpStatus.OK);
    }

}
