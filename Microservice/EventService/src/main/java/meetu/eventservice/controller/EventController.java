/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.controller;

import java.io.IOException;
import meetu.eventservice.service.EventService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import meetu.eventservice.model.Event;
import meetu.eventservice.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wdrdr
 */
@CrossOrigin(origins = "*")
@RestController
public class EventController {

    @Value("${spring.profiles.active}")
    List<String> profiles;

    @Autowired
    private EventService eventService;

    @Autowired
    private QRCodeService qRCodeService;

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<Event>(eventService.createEvent(event), HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> findAllEvents(
            @RequestParam(required = false) String eventDetail,
            @RequestParam(required = false) String[] eventTags
    ) throws IOException {
        if (eventDetail != null || eventTags != null) {
            return new ResponseEntity<List<Event>>(eventService.findByEventDetailInElastic(eventTags, eventDetail), HttpStatus.OK);
        }
        return new ResponseEntity<List<Event>>(eventService.findAllEventsInElastic(), HttpStatus.OK);
    }

    @GetMapping("/events/qrcode")
    public ResponseEntity<byte[]> qrCodeGenerator(HttpServletResponse response) {
        response.setContentType("image/png");
        // return new ResponseEntity<byte[]>(qRCodeService.getQRCodeImage(), HttpStatus.OK);
        return new ResponseEntity<byte[]>(qRCodeService.getQRCodeImage("https://trello.com/b/OutSJrmK/project", 1000, 1000), HttpStatus.OK);
    }

}
