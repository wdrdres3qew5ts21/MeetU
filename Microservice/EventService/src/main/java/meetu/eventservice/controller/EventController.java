/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.controller;

import meetu.eventservice.model.Category;
import java.io.IOException;
import java.util.HashMap;
import meetu.eventservice.service.EventService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import meetu.eventservice.model.Badge;
import meetu.eventservice.model.Event;
import meetu.eventservice.model.EventTicket;
import meetu.eventservice.model.User;
import meetu.eventservice.model.UserNotification;
import meetu.eventservice.model.UserEventTicket;
import meetu.eventservice.model.UserViewEvent;
import meetu.eventservice.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    String envTest = System.getenv("test");

    @Value("${boot.test}")
    String superDude;

    @Autowired
    private EventService eventService;

    @Autowired
    private QRCodeService qRCodeService;
    
    
//    @PostMapping("/badge")
//    public ResponseEntity createBadge(@RequestBody Badge badge) {
//        return eventService.createBadge(badge);
//    }
//    
//    @GetMapping("/badges")
//    public ResponseEntity<Badge> findEventThatMatchingBadge(
//            @RequestParam(required = false) List<String> badgeTags,
//            @RequestParam(required = false, defaultValue = "") String badgeName,
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "20") int contentPerPage ) {
//        return eventService.findEventThatMatchingBadge(badgeTags, badgeName,page,contentPerPage);
//    }

    @PostMapping("/event")
    public ResponseEntity createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    
    @GetMapping("/events/organize/{organizeId}")
    public ResponseEntity findAllEventOfOrganize(@PathVariable String organizeId) {
        return eventService.findAllEventOfOrganize(organizeId);
    }

    @PostMapping("/notification/token")
    public ResponseEntity saveUserNotificationToken(@RequestBody UserNotification userNotification) {
        return new ResponseEntity(eventService.saveuserNotification(userNotification), HttpStatus.CREATED);
    }

    @GetMapping("/events/tickets/{uid}")
    public ResponseEntity findUserTicketHistory(@PathVariable String uid) {
        return eventService.findUserTicketHistory(uid);
    }
    
    @GetMapping("/events/tickets/{uid}/{elasticEventId}")
    public ResponseEntity findUserTicketHistory(@PathVariable String uid, @PathVariable String elasticEventId) {
        return eventService.findUserTicketHistoryByElasticEventId(uid, elasticEventId);
    }

    @PostMapping("/event/join")
    public ResponseEntity userJoinEvent(@RequestBody UserEventTicket userJoinEvent) {
        return new ResponseEntity(eventService.userJoinEvent(userJoinEvent), HttpStatus.CREATED);
    }

    @PostMapping("/event/reserve")
    public ResponseEntity userReserveTicket(@RequestBody UserEventTicket userJoinEvent) {
        System.out.println("---- Reserve -----");
        return eventService.userReserveTicket(userJoinEvent);
    }

    @PostMapping("/event/view")
    public ResponseEntity userViewEvent(@RequestBody UserViewEvent userViewEvent) {
        System.out.println("---- User View Event -----");
        return eventService.userViewEvent(userViewEvent);
    }

    @DeleteMapping("/event/{elasticEventId}")
    public ResponseEntity deleteEventByElasticId(@PathVariable String elasticEventId) {
        return eventService.deleteEventByElasticId(elasticEventId);
    }

    @GetMapping("/events/popular")
    public ResponseEntity findAllPopularEvent() {
        return eventService.findAllPopularEvent();
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> searchWithFilter(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage,
            @RequestParam(required = false, defaultValue = "") String eventDetail,
            @RequestParam(required = false) String[] eventTags,
            @RequestParam(required = false, defaultValue = "false") boolean isRecently,
            @RequestParam(required = false, defaultValue = "false") boolean isPopularEvent,
            @RequestParam(required = false, defaultValue = "0.0", name = "lon") double longitude,
            @RequestParam(required = false, defaultValue = "0.0", name = "lat") double latitude,
            @RequestParam(required = false, defaultValue = "5km") String areaOfEvent
    ) throws IOException {
        System.out.println("search with filter");
        areaOfEvent = areaOfEvent.toLowerCase();
        eventDetail = eventDetail.toLowerCase();
        if (eventTags != null) {
            for (int i = 0; i < eventTags.length; i++) {
                eventTags[i] = eventTags[i].toLowerCase();
            }
        }
        return new ResponseEntity<List<Event>>(
                eventService.findEventByUsingFilter(
                        eventTags, isRecently, isPopularEvent, eventDetail,
                        longitude, latitude, areaOfEvent,
                        page, contentPerPage), HttpStatus.OK
        );
    }

    @GetMapping("/event/{elasticEventId}")
    public ResponseEntity<Event> findEventByElasticId(@PathVariable String elasticEventId) {
        return eventService.findEventByEventId(elasticEventId);
    }

    @PostMapping("/events/recommend/persona")
    public ResponseEntity searchWithPersonalize(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int contentPerPage,
            @RequestBody User user
    ) {
        return new ResponseEntity(eventService.findUserAndEventThatMatchingInDatabase(user), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity getAllEventCatagory() {
        return eventService.getAllEventCategory();
    }
    
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity deleteCategoryById(@PathVariable String categoryId) {
        System.out.println("---- User View Event -----");
        return eventService.deleteCategoryById(categoryId);
    }

    @PostMapping("/category")
    public ResponseEntity createEventCatagory(@RequestBody Category category) {
        return eventService.createEventCategory(category);
    }

//    @PostMapping("/events/recommend/persona")
//    public ResponseEntity<List<Event>> searchWithPersonalize(
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "20") int contentPerPage,
//            @RequestBody User user
//    ) {
//        return new ResponseEntity<List<Event>>(eventService.findEventByPersonalize(user), HttpStatus.OK);
//    }
    @GetMapping("/event/qrcode")
    public ResponseEntity<byte[]> qrCodeGenerator(HttpServletResponse response) {
        response.setContentType("image/png");
        return new ResponseEntity<byte[]>(qRCodeService.getQRCodeImage("https://trello.com/b/OutSJrmK/project", 1000, 1000), HttpStatus.OK);
    }

}
