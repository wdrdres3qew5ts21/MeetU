/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.controller;

import meetu.eventservice.service.EventService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import meetu.eventservice.model.Event;
import meetu.eventservice.service.QRCodeService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wdrdr
 */
@CrossOrigin(origins = "*")
@RestController
public class EventController {

   //@Value("${server.message.greeting}")
   private String eurekaMessage;
    
   @Value("${spring.profiles.active}")
   List<String> profiles;
   
    @GetMapping("/test")
    public ResponseEntity<String> testMessage() {
        return new ResponseEntity<String>(profiles.get(0)+" : "+eurekaMessage, HttpStatus.OK);
    }

    @Autowired
    private EventService eventService;

    @Autowired
    private QRCodeService qRCodeService;

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<Event>(eventService.createEvent(event), HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> findAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.findAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/events/qrcode")
    public ResponseEntity<byte[]> qrCodeGenerator(HttpServletResponse response) {
        // 
        response.setContentType("image/png");
       // return new ResponseEntity<byte[]>(qRCodeService.getQRCodeImage(), HttpStatus.OK);
        return new ResponseEntity<byte[]>(qRCodeService.getQRCodeImage("https://trello.com/b/OutSJrmK/project",1000 , 1000), HttpStatus.OK);
        
    }
}
