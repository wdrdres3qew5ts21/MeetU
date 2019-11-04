/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import meetu.eventservice.repository.BadgeRepository;
import meetu.eventservice.repository.CategoryRepository;
import meetu.eventservice.repository.EventBadgeRepository;
import meetu.eventservice.repository.EventRepository;
import meetu.eventservice.repository.UserEventTicketRepository;
import meetu.eventservice.repository.UserNotificationRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Test
 */
@Component
public class EventScheduler {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserEventTicketRepository userEventTicketRespository;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 60000)
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
    }

}
