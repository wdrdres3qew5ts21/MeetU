/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.Message;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.eventservice.config.ElasticUtil;
import meetu.eventservice.model.Event;
import meetu.eventservice.model.InterestGenreBehavior;
import meetu.eventservice.model.Persona;
import meetu.eventservice.model.User;
import meetu.eventservice.model.UserNotification;
import meetu.eventservice.repository.EventRepository;
import meetu.eventservice.repository.EventTicketRespository;
import meetu.eventservice.repository.UserNotificationRepository;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;
import com.google.firebase.messaging.WebpushNotification;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Aggregates;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.beans.Expression;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import meetu.eventservice.controller.EventController;
import meetu.eventservice.model.Badge;
import meetu.eventservice.model.BadgeReward;
import meetu.eventservice.model.Category;
import meetu.eventservice.model.EventBadge;
import meetu.eventservice.model.Organize;
import meetu.eventservice.model.Review;
import meetu.eventservice.model.UserEventTicket;
import meetu.eventservice.model.UserJoinEvent;
import meetu.eventservice.model.UserViewEvent;
import meetu.eventservice.repository.UserEventTicketRepository;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import meetu.eventservice.repository.CategoryRepository;
import meetu.eventservice.repository.EventBadgeRepository;
import meetu.eventservice.repository.ReviewRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.elasticsearch.action.update.UpdateRequest;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author wdrdr
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserEventTicketRepository userEventTicketRespository;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventBadgeRepository eventBadgeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestHighLevelClient elasticClient;

    @Value("${event.service}")
    private String EVENTSERVICE_URL;

    @Value("${user.service}")
    private String USERSERVICE_URL;

    @Value("${community.service}")
    private String COMMUNITYSERVICE_URL;

    private String eventsIndex = "meetu.events";
    
    private String newEventTopic = "new-event";

    public void pushNotificationToAllUserWhenEventCreate(String eventName, String eventDetail) {
        // Create a list containing up to 100 messages.
        List<UserNotification> userNotifications = userNotificationRepository.findAll();
        List<Message> messages = new ArrayList<>();

        if (userNotifications != null) {
            for (UserNotification userNotification : userNotifications) {
                messages = Arrays.asList(
                        Message.builder()
                                .setNotification(new Notification(
                                        eventName,
                                        eventDetail))
                                .setToken(userNotification.getNotificationToken())
                                .build()
                );
            }
            BatchResponse response = null;
            try {
                response = FirebaseMessaging.getInstance().sendAll(messages);
            } catch (FirebaseMessagingException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(response.getSuccessCount() + " messages were sent successfully");
        }

    }

    public ResponseEntity createEvent(Event event) {
        Date currentDate = new Date();
        event.setCreateEventDate(currentDate);

        ResponseEntity<Badge> badgeResponse = restTemplate.getForEntity(USERSERVICE_URL + "/badge/" + event.getBadge().getBadgeId(), Badge.class);
        Badge badgeInService = badgeResponse.getBody();
        HashMap<String, String> response = new HashMap();

        if (badgeInService == null) {
            response.put("response", "Fail to create Event Because Badge ID Not found : ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        String organizeId = event.getOrganize().getOrganizeId();
        System.out.println(organizeId);
        ResponseEntity<Organize> organizeInDatabase = restTemplate.getForEntity(USERSERVICE_URL + "/organize/" + organizeId, Organize.class);
        System.out.println(organizeInDatabase.getBody());

        if (organizeInDatabase.getBody() == null) {
            response.put("response", "Fail to create Event Because Organize ID Not found : ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        System.out.println(organizeInDatabase);
        System.out.println("----- Organize Founded -------");
        System.out.println(organizeInDatabase.getBody());
        event.setOrganize(organizeInDatabase.getBody());
        BadgeReward badge = event.getBadge();
        badge.setBadgeName(badgeInService.getBadgeName());
        badge.setBadgePicture(badgeInService.getBadgePicture());
        badge.setBadgeTags(badgeInService.getBadgeTags());

        EventBadge eventBadge = new EventBadge();
        eventBadge.setBadge(badgeInService);
        eventBadge.setEvent(event);
        eventBadgeRepository.save(eventBadge);

        IndexRequest indexRequest = new IndexRequest(eventsIndex);
        String elasticEventid = null;
        Map<String, Object> pojoToMap = ElasticUtil.pojoToMap(event);
        //pojoToMap.remove("organize");
        System.out.println(pojoToMap);
        indexRequest.source(pojoToMap);
        try {
            IndexResponse indexResponse = elasticClient.index(indexRequest, RequestOptions.DEFAULT);
            elasticEventid = indexResponse.getId();
            event.setElasticEventId(elasticEventid);
        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Event savedEventMongoDB = null;
        try {
            savedEventMongoDB = eventRepository.save(event);
            System.out.println(savedEventMongoDB);
            // push notification for created event
            UserNotification userNotification = new UserNotification();
            userNotification.setMessageDetail("Recently event : " + savedEventMongoDB.getEventName());
            userNotification.setLinkUrl("/event/" + elasticEventid);
            userNotification.setPictureUrl(savedEventMongoDB.getEventPictureCover());
            this.pushNotificationTopic(userNotification, this.newEventTopic);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            savedEventMongoDB = eventRepository.save(event);
            if (savedEventMongoDB == null) {
                DeleteRequest deleteRequest = new DeleteRequest(eventsIndex);
                deleteRequest.id(elasticEventid);
                try {
                    elasticClient.delete(deleteRequest, RequestOptions.DEFAULT);
                } catch (IOException ex1) {
                    Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEventMongoDB);
    }

    public ResponseEntity<HashMap<String, Object>> deleteEventByElasticId(UserEventTicket deletedEvent) {
        HashMap<String, Object> responseBody = new HashMap();
        Event deletedEventMongo = null;
        String elasticEventId = deletedEvent.getElasticEventId();
        DeleteRequest deleteRequest = new DeleteRequest(eventsIndex, elasticEventId);
        DeleteResponse deleteResponse = null;
        Event eventForDelete = eventRepository.findByElasticEventId(elasticEventId);
        if (eventForDelete != null) {
            if (deletedEvent.getConfirmDelete().equals("confirmed") & !deletedEvent.getDeleteMessageDetail().isEmpty()) {
                System.out.println("--- delete detail -----");
                System.out.println(deletedEvent);
                try {
                    deleteResponse = elasticClient.delete(deleteRequest, RequestOptions.DEFAULT);
                } catch (IOException ex) {
                    Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
                    System.out.println(deleteResponse);
                    try {
                        System.out.println("---- initial delete item ----");
                        // แสดงรายละเอียดการลบกิจกรรมไปยังตั๋ว Ticket ที่ User ครอบครอง
                        Query query = Query.query(Criteria.where("elasticEventId").is(elasticEventId));
                        Update updateDeletedEventDetailToUserTicket = new Update();
                        updateDeletedEventDetailToUserTicket.set("deleteMessageDetail", deletedEvent.getDeleteMessageDetail());
                        updateDeletedEventDetailToUserTicket.set("isEventDelete", true);
                        mongoTemplate.findAndModify(query, updateDeletedEventDetailToUserTicket, UserEventTicket.class);
                        // ลบตั๋วจริงๆออกจาก MongoDB
                        eventRepository.deleteByElasticEventId(elasticEventId);
                        System.out.println("---- Deleted Event Mongo ----");

                        responseBody.put("response", "Delete Contenet successs");
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);

                    } catch (Exception ex) {
                        Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    responseBody.put("response", "remove " + elasticEventId + " fail !");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
                }

            } else {
                responseBody.put("response", "Please check your confirmation again !");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }
        }

        responseBody.put("response", "Please check your confirmation again !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public List<Event> findEventByUsingFilter(String[] eventTags, boolean isRecently, String sortDate, boolean isPopularEvent, String eventDetail, double longitude, double latitude, String areaOfEvent, int page, int contentPerPage) throws IOException {
        BoolQueryBuilder queryFilter = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(eventsIndex);
        SearchResponse searchResponse = null;

        System.out.println("--- Start Filtering ---");
        if (eventTags != null) {
            System.out.println("Event Tag Filter");
            queryFilter = filterByEventTags(queryFilter, eventTags);
        }
        if (!eventDetail.isEmpty()) {
            System.out.println("Event Detail Filter");
            queryFilter.must(filterByEventDetail(eventDetail));
        }
        if (isRecently == true) {
            System.out.println("Recently Filter");
            searchSourceBuilder = filterByRecently(searchSourceBuilder, "createEventDate");
        }
        if (!sortDate.isEmpty()) {
            System.out.println("--- date osrt filter ----");
            if (sortDate.equals("asc")) {
                searchSourceBuilder.sort(new FieldSortBuilder("eventStartDate").order(SortOrder.ASC));
                //  searchSourceBuilder.sorts().add(new FieldSortBuilder("eventStartDate").order(SortOrder.ASC));
            } else {
                searchSourceBuilder.sort(new FieldSortBuilder("eventStartDate").order(SortOrder.DESC));
                // searchSourceBuilder.sorts().add(new FieldSortBuilder("eventStartDate").order(SortOrder.DESC));
            }
        }
        if (isPopularEvent == true) {
            System.out.println("Popular Event Filter");
            // filter ต้องอยู่ในช่วงเวลา
            // queryFilter.must(QueryBuilders.rangeQuery("endRegisterDate").lte("now-1d/d"));
            searchSourceBuilder.sort(new FieldSortBuilder("totalView").order(SortOrder.DESC));
            //  searchSourceBuilder.sorts().add(new FieldSortBuilder("totalView").order(SortOrder.DESC));
        }
        if (longitude != 0.0 & latitude != 0.0) {
            System.out.println("Geo Filter");
            queryFilter.filter(filterByGeolocation(latitude, longitude, areaOfEvent));
            searchSourceBuilder.sort(
                    new GeoDistanceSortBuilder("location.geopoint", latitude, longitude)
                            .unit(DistanceUnit.KILOMETERS)
                            .order(SortOrder.ASC)
            );
        }

        searchSourceBuilder.query(queryFilter);
        searchSourceBuilder
                .from(page)
                .size(contentPerPage);
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchRequest.indices());
        searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits());

        return ElasticUtil.searchHitsToList(searchResponse.getHits(), Event.class);
    }

    public ResponseEntity findUserAndEventThatMatchingInDatabase(User user) {
        System.out.println(user);
        String uid = user.getUid();
        if (uid != null) {
            User userInDatabase = restTemplate.getForObject(USERSERVICE_URL + "/user/" + uid, User.class);
            System.out.println("------ User From Database -------");
            System.out.println(userInDatabase);
            System.out.println("-----User Personalize ---------");
            System.out.println(userInDatabase.getPersona().getInterestBehaviorList());
            return this.personalizeEventForUser(userInDatabase);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(personalizeEventForUser(user));
        }
    }

    public ResponseEntity personalizeEventForUser(User user) {
        BoolQueryBuilder queryFilter = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(eventsIndex);
        SearchResponse searchResponse = null;
        Persona persona = user.getPersona();
        List<String> interestIdeaList = persona.getInterestIdea();
        List<InterestGenreBehavior> interestBehaviorList = persona.getInterestBehaviorList();
        byte numberOfRecommendationSize = 3; // จำนวน genre ที่สามารถใช้ recommend ได้เช่นมีทั้งหมด 3 ประเภท, 5 ประเภท
        int participateBoundedForWeightGenre = 2;// จำนวนขั้นต่ำของจำนวนครั้งที่เข้าร่วมกิจกรรมจึงจะนำมา weight ในการเลือก top N กิจกรรมที่เข้าร่วม
        System.out.println("Find Event By Personalize ! ");

        //Logic Recommendation
        // ขนาดที่เราจะเก็บจำนวนที่ recommendation ได้คือ numberOfRecommendationSize ถ้าเกิดจากนี้ก็จะเตะตัวที่น้อยสุดสุดออกและนำตัวใหม่เข้ามาแทนนั่นเอง
        ArrayList<InterestGenreBehavior> topNumberParticipateEvent = new ArrayList(numberOfRecommendationSize);
        int numberOfGenreThatInsideTopNListParticipate = 0;// ตัวแปรที่ใช้วัดว่าจำนวน top numberOfRecommendationSize ที่เก็บลงไปใน array ของ topNParticipate ถึงแค่ไหนแล้ว
        for (int i = 0; i < interestBehaviorList.size(); i++) {
            if (interestBehaviorList.get(i).getTotalParticipate() >= participateBoundedForWeightGenre) {
                if (numberOfGenreThatInsideTopNListParticipate < numberOfRecommendationSize) {
                    topNumberParticipateEvent.add(interestBehaviorList.get(i));
                    numberOfGenreThatInsideTopNListParticipate++;
                } else {
                    int indexOfEventThatHaveLowestScoreFromTopNParticipateList = topNumberParticipateEvent.indexOf(Collections.min(topNumberParticipateEvent));
                    if (interestBehaviorList.get(i).compareTo(topNumberParticipateEvent.get(indexOfEventThatHaveLowestScoreFromTopNParticipateList)) == 1) {
                        topNumberParticipateEvent.set(indexOfEventThatHaveLowestScoreFromTopNParticipateList, interestBehaviorList.get(i));
                    }
                }
            }
        }
        System.out.println("------ top 3 participate event (Reverse Order) -------");
        System.out.println("Total TopRank Slot : " + numberOfGenreThatInsideTopNListParticipate);
        topNumberParticipateEvent.sort(Collections.reverseOrder());
        System.out.println("top3Participate : " + topNumberParticipateEvent);

        // หลังจากที่เราได้จำนวนกิจกรรม top N ที่เข้าร่วมมาแล้ว 
        // เราจะทำการเช็คการ interestIdea ที่เขาได้ทำการ check เอาไว้ว่าสนใจในด้านใดมา weight ร่วมกับกิจกรรมที่เขาเข้าร่วม
        // ถ้าเกิด topNumberParticipate นั้นไม่มีเลยสักตัวเราก็ต้องนำตัวที่เขา check คือ ideaInterest ไปใช้แทนแต่ถ้าเป็น genre ที่สนใจตัวเดียวกันอีกก็จะยิ่ง boost เพิ่ม
        List<QueryBuilder> queryBuilderList = new ArrayList<>();
        for (int i = 0; i < numberOfGenreThatInsideTopNListParticipate; i++) {
            if (interestIdeaList.contains(topNumberParticipateEvent.get(i))) {
                // ถ้า InterestIdea ที่เลือกไว้จาก preference ตรงกับ  TopN ที่เราคัดกรองมาก็จะบวกคะแนน
                queryFilter.should().add(
                        QueryBuilders.termQuery("eventTags", topNumberParticipateEvent.get(i).getGenre().toLowerCase())
                //                                .boost(1.2f)
                );
            } else {
                queryFilter.should().add(
                        QueryBuilders.termQuery("eventTags", topNumberParticipateEvent.get(i).getGenre().toLowerCase()));
            }
        }

        for (int i = 0; i < numberOfGenreThatInsideTopNListParticipate; i++) {
            if (interestBehaviorList.contains(topNumberParticipateEvent.get(i))) {
                System.out.println("remove ! " + topNumberParticipateEvent.get(i));
                interestBehaviorList.remove(topNumberParticipateEvent.get(i));
            }
        }
        System.out.println("behavior filtered : " + interestBehaviorList);

        ArrayList<InterestGenreBehavior> slotForFreeSpaceOfTopNRank = new ArrayList<>(10);
        int numberSlotForFreeSpaceOfTopNRank = 0;
        for (int i = 0; i < (numberOfRecommendationSize - numberOfGenreThatInsideTopNListParticipate); i++) {
            int indexOfEventThatHaveHighestScoreFromBehaviorListAfterFilter = interestBehaviorList.indexOf(Collections.max(interestBehaviorList));
            System.out.println("Free Slot Seletected : " + interestBehaviorList.get(indexOfEventThatHaveHighestScoreFromBehaviorListAfterFilter));
            slotForFreeSpaceOfTopNRank.add(interestBehaviorList.get(indexOfEventThatHaveHighestScoreFromBehaviorListAfterFilter));
            interestBehaviorList.remove(interestBehaviorList.get(indexOfEventThatHaveHighestScoreFromBehaviorListAfterFilter));
            numberSlotForFreeSpaceOfTopNRank++;
        }

        System.out.println("--- Slot For Free Space -----");
        System.out.println("Total Free Slot : " + numberSlotForFreeSpaceOfTopNRank);
        System.out.println(slotForFreeSpaceOfTopNRank.toString());
        for (int i = 0; i < numberSlotForFreeSpaceOfTopNRank; i++) {
            if (interestIdeaList.contains(slotForFreeSpaceOfTopNRank.get(i))) {
                // ถ้า InterestIdea ที่เลือกไว้จาก preference ตรงกับ  TopN ที่เราคัดกรองมาก็จะบวกคะแนน
                queryFilter.should().add(
                        QueryBuilders.termQuery("eventTags", slotForFreeSpaceOfTopNRank.get(i).getGenre().toLowerCase())
                                .boost(1.2f)
                );
            } else {
                queryFilter.should().add(QueryBuilders.termQuery("eventTags", slotForFreeSpaceOfTopNRank.get(i).getGenre().toLowerCase()));
            }
        }
        searchSourceBuilder.query();
        searchRequest.source(searchSourceBuilder);
        try {
            searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(ElasticUtil.searchHitsToList(searchResponse.getHits(), Event.class));
    }

    public BoolQueryBuilder filterByEventTags(BoolQueryBuilder queryFilter, String eventTags[]) {
        queryFilter.filter(QueryBuilders.termsQuery("eventTags", eventTags));
        return queryFilter;
    }

    public QueryStringQueryBuilder filterByEventDetail(String eventDetail) {
        QueryStringQueryBuilder alreadyFilterByEventDetail = QueryBuilders.queryStringQuery(eventDetail + "~")
                .field("eventName").boost(3.0f)
                .field("eventDetail").boost(3.0f)
                .field("location.*").boost(2.0f)
                .fuzzyTranspositions(true);
        return alreadyFilterByEventDetail;
    }

    public GeoDistanceQueryBuilder filterByGeolocation(double latitude, double longitude, String areaOfEvent) {
        return QueryBuilders.geoDistanceQuery("location.geopoint")
                .point(latitude, longitude)
                .distance(areaOfEvent);
    }

    public SearchSourceBuilder filterByRecently(SearchSourceBuilder searchSourceBuilder, String sortedField) throws IOException {
        searchSourceBuilder.sort(new FieldSortBuilder(sortedField).order(SortOrder.DESC));
        //  searchSourceBuilder.sorts().add(new FieldSortBuilder(sortedField).order(SortOrder.ASC));
        return searchSourceBuilder;
    }

    public ResponseEntity findEventByEventId(String eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventRepository.findByElasticEventId(eventId));
    }

    public ResponseEntity findEventByElasticId(String elasticEventId) {
        GetResponse getResponse = null;
        GetRequest getRequest = new GetRequest(eventsIndex, elasticEventId);
        HashMap<String, Object> responseBody = new HashMap<>();
        try {
            getResponse = elasticClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-------- Response -----------");
        System.out.println(getResponse);
        System.out.println(getResponse.isExists());
        if (getResponse.isExists()) {
            ObjectMapper mapper = new ObjectMapper();
            Event convertValue = mapper.convertValue(getResponse.getSource(), Event.class);
            return ResponseEntity.status(HttpStatus.OK).body(convertValue);
        }
        responseBody.put("response", "Not found EventID: " + elasticEventId + " !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public ResponseEntity userJoinEvent(String token, UserEventTicket userJoinEvent) {
        HashMap<String, Object> responseBody = new HashMap<>();
        System.out.println("USer Join Event From Frpntend");
        System.out.println(userJoinEvent);
        System.out.println("------ Rest Template ------");
        System.out.println(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);
        UserEventTicket qrCodeTicket = userEventTicketRespository.findByTicketId(userJoinEvent.getTicketId());
        String ticketOrganizeId = qrCodeTicket.getOrganize().getOrganizeId();
        System.out.println("-- organizeId ---");
        System.out.println(ticketOrganizeId);
        try {
            // request ไปถามเพื่อยืนยันว่าเราคือ admin ของ organize นี้จริงๆและมีสิทธิอยู่จริงๆด้วยนะ
            ResponseEntity<HashMap> adminStatusResponse = restTemplate.exchange(USERSERVICE_URL + "/organize/" + ticketOrganizeId + "/admin/status", HttpMethod.GET, entity, HashMap.class);
            // ResponseEntity<HashMap> adminStatusResponse = restTemplate.getForEntity(USERSERVICE_URL + "/organize/" + userJoinEvent.getOrganizeId() + "/admin/status", HashMap.class);
            if (adminStatusResponse.getStatusCode() == HttpStatus.OK) {
                // response กลับมา 200 แสดงว่าเป็น admin ไม่ก็ owner ก็แสดงว่าสามารถแสกนเข้าระบบได้
                UserEventTicket userEventTicketInDatabase = userEventTicketRespository.findByTicketId(userJoinEvent.getTicketId());
                if (userEventTicketInDatabase != null) {

                    System.out.println("!! userEventTicket !!");
                    System.out.println(userEventTicketInDatabase);
                    if (userEventTicketInDatabase.isIsParticipate() == false) {
                        if (userEventTicketInDatabase.getTicketKey().equals(userJoinEvent.getTicketKey()) && userEventTicketInDatabase.getUid().equals(userJoinEvent.getUid())) {
                            // logic ถูกต้อง validate ตั๋วจริงๆว่ามาจากระบบของเรา
                            // ทำการบันทึกว่าตั๋วนี้ได้ถูกใช้ไปหลัง check in สำเร็จและ update exp พร้อม interest ของผู้ใช้งานไปยัง UserService
                            Event eventFromDatabase = eventRepository.findByElasticEventId(userEventTicketInDatabase.getElasticEventId());
                            userEventTicketInDatabase.setIsParticipate(true);
                            userEventTicketInDatabase.setParticipateDate(new Timestamp(System.currentTimeMillis()));
                            userEventTicketInDatabase.setBadgeId(eventFromDatabase.getBadge().getBadgeId());
                            userEventTicketInDatabase.setExp(eventFromDatabase.getBadge().getExp());
                            restTemplate.postForEntity(USERSERVICE_URL + "/user/interest", userEventTicketInDatabase, UserJoinEvent.class);
                            return ResponseEntity.status(HttpStatus.CREATED).body(userEventTicketRespository.save(userEventTicketInDatabase));
                        }
                    } else {
                        responseBody.put("response", "This ticket had been usage !!!");
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
                    }
                }

            }
        } catch (HttpStatusCodeException exception) {
            if (exception.getStatusCode().UNAUTHORIZED == HttpStatus.UNAUTHORIZED) {
                responseBody.put("response", "[Unauthorize] You aren't Admin of " + qrCodeTicket.getOrganize().getOrganizeName() + " organize !!!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }

        }

        responseBody.put("response", "This ticket is wrong perhaps not our ticket !!!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public ResponseEntity userReserveTicket(UserEventTicket userReserveTicket) {
        HashMap<String, Object> responseBody = new HashMap<>();
        Event eventInDatabase = eventRepository.findByElasticEventId(userReserveTicket.getElasticEventId());
        if (eventInDatabase != null) {
            if (eventInDatabase.getNumberOfTicket() > 0) {
                if (eventInDatabase.getUserLists().contains(userReserveTicket.getUid()) == false) {
                    // บันทึกรายละเอียดของตั๋วที่กดมาจาก Event นั้นลง UserEventTicket
                    byte[] array = new byte[8]; // length is bounded by 7
                    new Random().nextBytes(array);
                    String generateTicketKey = new String(array, Charset.forName("UTF-8"));
                    userReserveTicket.setTicketId(System.currentTimeMillis() + "");
                    userReserveTicket.setEventName(eventInDatabase.getEventName());
                    userReserveTicket.setOrganize(eventInDatabase.getOrganize());
                    userReserveTicket.setEventTags(eventInDatabase.getEventTags());
                    userReserveTicket.setTicketKey(RandomStringUtils.randomAlphanumeric(8));
                    System.out.println("Event In Data Base");
                    System.out.println(eventInDatabase);
                    System.out.println(userReserveTicket);
                    // หักจำนวนตั๋วที่เหลืออยู่ในระบบ
                    int numberOfTicket = eventInDatabase.getNumberOfTicket();
                    numberOfTicket--;
                    List<String> userLists = eventInDatabase.getUserLists();
                    userLists.add(userReserveTicket.getUid());
                    eventInDatabase.setUserLists(userLists);
                    eventInDatabase.setNumberOfTicket(numberOfTicket);
                    eventRepository.save(eventInDatabase);
                    System.out.println("Saved User Event Tikcet");
                    UserEventTicket savedUserEventTicket = userEventTicketRespository.save(userReserveTicket);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserEventTicket);
                } else {
                    responseBody.put("response", "You already have ticket !!");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
                }
            } else {
                responseBody.put("response", "Ticket had been sold out !");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
            }
        }
        responseBody.put("response", "Event not available for sold ticket now !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public ResponseEntity saveuserNotification(UserNotification userNotification) {
        UserNotification userNotificationInDatabase = userNotificationRepository.findByUid(userNotification.getUid());
        // บันทึก Token ครั้งแรกกรณีที่ผู้ใช้ไม่เคยมี Token เลย
        if (userNotificationInDatabase == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userNotificationRepository.save(userNotification));
        }
        System.out.println("New Token From User : " + userNotification.getNotificationToken());
        // อัพเดท Token ตัวใหม่ไปให้แก่ User ตาม uid นั้นๆหากเคยมี Token อยู่แล้ว
        userNotificationInDatabase.setNotificationToken(userNotification.getNotificationToken());
        System.out.println("-------------------- Notification DAtabase -------------------");
        System.out.println(userNotificationInDatabase);
        return ResponseEntity.status(HttpStatus.CREATED).body(userNotificationRepository.save(userNotificationInDatabase));
    }

//    @HystrixCommand(fallbackMethod = "fuckYouFallback")
    public ResponseEntity userViewEvent(UserViewEvent userViewEvent) {
        Map<String, Object> response = new HashMap();
        System.out.println("User View Event");
        System.out.println(userViewEvent);

        String elasticEventId = userViewEvent.getElasticEventId();
        String uid = userViewEvent.getUid();
        Event eventInDatabase = eventRepository.findByElasticEventId(elasticEventId);
        System.out.println("Event in database");
        System.out.println(eventInDatabase);
        int totalView = eventInDatabase.getTotalView();
        totalView++;
        eventInDatabase.setTotalView(totalView);
        Event saveEventMongo = eventRepository.save(eventInDatabase);
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(this.eventsIndex);
        updateRequest.id(userViewEvent.getElasticEventId());
        try {
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("totalView", totalView)
                    .endObject());
            DocWriteResponse.Result result = elasticClient.update(updateRequest, RequestOptions.DEFAULT).getResult();
            System.out.println(saveEventMongo);
            System.out.println("---- Result-----");
            System.out.println(result);
            System.out.println("--- Source ----");

        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (saveEventMongo != null) {
            List<String> eventTags = eventInDatabase.getEventTags();
            userViewEvent.setEventTags(eventTags);
            if (userViewEvent.getUid() != null) {
                System.out.println("User have login");
                return restTemplate.postForEntity(USERSERVICE_URL + "/user/view", userViewEvent, UserViewEvent.class);
            } else {
                System.out.println("Dont have user login");
                return ResponseEntity.status(HttpStatus.CREATED).body(saveEventMongo);
            }
            // return ResponseEntity.status(HttpStatus.CREATED).body(saveEventMongo);
        }
        response.put("response", "failed to update Event view");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    // join เพื่อหา Detail ของ Event ที่ตั๋วนั้นถูกกดมาใช้งาน
    public ResponseEntity findUserTicketHistory(String uid) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("events")
                .localField("elasticEventId")
                .foreignField("elasticEventId")
                .as("ticketDetail");
        AggregationOperation match = Aggregation.match(Criteria.where("uid").is(uid));
        SortOperation sortByTicketCreateTime = sort(new Sort(Sort.Direction.DESC, "_id"));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, match, sortByTicketCreateTime);
        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "userEventTicket", BasicDBObject.class).getMappedResults();
        System.out.println("----------------------");
        System.out.println(results);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    public ResponseEntity findUserTicketHistoryNoAggregate(String uid) {
        List<UserEventTicket> ticketsOfUser = userEventTicketRespository.findByUid(uid);
        return ResponseEntity.status(HttpStatus.OK).body(ticketsOfUser);
    }

    public ResponseEntity createEventCategory(Category category) {
        HashMap<String, String> responseBody = new HashMap<>();
        Category savedCatagory = categoryRepository.save(category);
        if (savedCatagory != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCatagory);
        }
        responseBody.put("response", "Failed to create new Catagory");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public ResponseEntity getAllEventCategory() {
        List<Category> categoryList = categoryRepository.findAllByOrderByCategoryLabelAsc();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    public ResponseEntity findAllPopularEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResponseEntity deleteCategoryById(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity findUserTicketHistoryByElasticEventId(String uid, String elasticEventId) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("events")
                .localField("elasticEventId")
                .foreignField("elasticEventId")
                .as("ticketDetail");
        AggregationOperation uidMatch = Aggregation.match(Criteria.where("uid").is(uid));
        AggregationOperation elasticMatch = Aggregation.match(Criteria.where("elasticEventId").is(elasticEventId));
        List<AggregationOperation> aggregateList = new ArrayList<>();
        aggregateList.add(uidMatch);
        aggregateList.add(elasticMatch);
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, uidMatch, elasticMatch);
        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "userEventTicket", BasicDBObject.class).getMappedResults();
        System.out.println("----------------------");
        System.out.println(results);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    public ResponseEntity findAllEventOfOrganize(String organizeId) {
        List<Event> allEventOfOrganize = eventRepository.findByOrganizeOrganizeId(organizeId);
        System.out.println("find all event ");
        System.out.println(allEventOfOrganize);
        return new ResponseEntity(allEventOfOrganize, HttpStatus.OK);
    }

    public ResponseEntity deleteEventByElasticIdTest(UserEventTicket deletedEvent) {
        System.out.println(deletedEvent);
        String elasticEventId = deletedEvent.getElasticEventId();
        Query query = Query.query(Criteria.where("elasticEventId").is(elasticEventId));
        Update updateDeletedEventDetailToUserTicket = new Update();
        updateDeletedEventDetailToUserTicket.set("deleteMessageDetail", deletedEvent.getDeleteMessageDetail());
        updateDeletedEventDetailToUserTicket.set("isEventDelete", true);

        mongoTemplate.findAndModify(query, updateDeletedEventDetailToUserTicket, UserEventTicket.class);

        return null;
    }

    public ResponseEntity pushNotificationForRemindEvent() {
        Event event = eventRepository.findByElasticEventId("KL3RO24BGta3uOKHWgJy");
        Date nowDate = new Date();
        Date createEventDate = event.getEventStartDate();

        long diff = createEventDate.getTime() - nowDate.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((createEventDate.getTime() - nowDate.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println("diff " + diff);
        System.out.println("diff day " + diffInDays);
        System.out.println("diff hour " + diffHours);
        System.out.println("diff min " + diffMinutes);
        System.out.println("diff sec " + diffSeconds);
        // "{$expr:{$gt:[ {'$subtract':[  '$date' ,  '$eventStartDate'  ]}, 1000*60*60*2 ]}}"
        //BasicDBObject parse = BasicDBObject.parse("{'$gte':[{'$subtract':[ {'$ifNull':['$acceptedDate', {'$date': " + System.currentTimeMillis() + "}]}, '$eventStartDate']},1296000000]}" );
        long DAY_ELASPE = 86400000;

        // work code
//        Query query = new BasicQuery("{$expr: {$gte:[{\"$subtract\":[\"$date\",\"$eventStartDate\"]}, "+  DAY_ELASPE +"]} }"   );
        Query query = new BasicQuery("{$expr: {$gte:[{\"$subtract\":[ new Date() ,\"$eventStartDate\"]}, " + DAY_ELASPE + "]} }");
        //   query=  new BasicQuery(" { $project: { time: { $subtract: [ new Date() , \"$eventStartDate\" ] } } }");
        ProjectionOperation projectionOperation = new ProjectionOperation();
        // ต้องลบกันแล้วเพิ่มเวลาออกมาจากระบบ จากนั้นนำเวลาไปเข้า if else push

        // mongoTemplate.aggregate(Aggregates.addFields("dateDifferecne",) , BasicDBObject.class)
        List<BasicDBObject> find = mongoTemplate.find(query, BasicDBObject.class, "events");

        if (diffInDays > 1) {
            System.err.println("Difference in number of days (2) : " + diffInDays);
        } else if (diffHours > 24) {
            System.err.println(">24");
        } else if ((diffHours == 24) && (diffMinutes >= 1)) {
            System.err.println("minutes");
        }

        HashMap<String, String> response = new HashMap<>();

        return ResponseEntity.status(HttpStatus.OK).body(find);
    }

    public ResponseEntity forcePushNotificationFromAdmin(String token, String elasticEventId, String messageDetail) throws FirebaseMessagingException {
        System.out.println("-- force push ---");
        Event elasticEventInDatabase = eventRepository.findByElasticEventId(elasticEventId);
        HashMap<String, String> response = new HashMap<>();
        if (elasticEventInDatabase != null) {
            List<String> userListUid = elasticEventInDatabase.getUserLists();
            List<UserNotification> userNotificationList = userNotificationRepository.findByUidIsIn(userListUid);
            if (userNotificationList != null) {
                elasticEventInDatabase.getEventStartDate();
                List<Message> messages = new ArrayList<>();
                HashMap<String, String> dataPayload = new HashMap<>();
                dataPayload.put("link", "https://meetu-69b29.firebaseapp.com/event/" + elasticEventId);
                for (UserNotification userNotification : userNotificationList) {
                    messages = Arrays.asList(
                            Message.builder()
                                    .setNotification(new Notification(
                                            elasticEventInDatabase.getEventName(),
                                            messageDetail))
                                    .setToken(userNotification.getNotificationToken())
                                    .setWebpushConfig(WebpushConfig.builder().putAllData(dataPayload).build())
                                    .build()
                    );
                }
                BatchResponse responsePush = null;
                try {
                    responsePush = FirebaseMessaging.getInstance().sendAll(messages);
                } catch (FirebaseMessagingException ex) {
                    Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(responsePush.getSuccessCount() + " messages were sent successfully");
                response.put("response", responsePush.getSuccessCount() + " messages were sent successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("response", "Not have any user to push notification");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } else {
            response.put("response", "Not found this event : " + elasticEventId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public void pushNotificationForUserThatHaveTicket(String elasticEventId, String messageDetail) {
        return;
    }

    public ResponseEntity subscribeEventTopic(String notificationToken, String topic) {
        List<String> registrationTokens = Arrays.asList(
                notificationToken
        );
        TopicManagementResponse response;
        try {
            response = FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, topic);
            System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
        } catch (FirebaseMessagingException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity userReviewEvent(String token, Review userReview) {
        HashMap<String, String> response = new HashMap<>();
        Event eventInDatabase = eventRepository.findByElasticEventId(userReview.getElasticEventId());
        System.out.println("--- Review ----");
        System.out.println(userReview);
        token = token.replace("Bearer ", "");
        if (eventInDatabase != null) {
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                String uid = decodedToken.getUid();
                try {
                    ResponseEntity<User> userResponse = restTemplate.getForEntity(USERSERVICE_URL + "/user/" + uid, User.class);
                    User user = userResponse.getBody();
                    UserEventTicket eventThatUserJoin = userEventTicketRespository.findByUidAndElasticEventIdAndIsParticipate(uid, eventInDatabase.getElasticEventId(), true);
                    if (eventThatUserJoin != null) {
                        userReview.setDisplayName(user.getDisplayName());
                        userReview.setPhotoURL(user.getPhotoURL());
                        userReview.setUid(user.getUid());
                        userReview.setReviewDate(new Date());
                        List<Review> reviewList = eventInDatabase.getReviewList();
                        reviewList.add(userReview);
                        reviewRepository.saveAll(reviewList);
                        eventRepository.save(eventInDatabase);
                        response.put("response", "Not Found Event To Review !!!");
                        return ResponseEntity.status(HttpStatus.CREATED).body(userReview);
                    }
                } catch (HttpStatusCodeException codeException) {
                    if (codeException.getStatusCode() == HttpStatus.NOT_FOUND) {
                        response.put("response", "Not found this user so you can't comment!!");
                    };
                }

            } catch (FirebaseAuthException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                response.put("response", "You need to be login !");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        response.put("response", "Not Found Event To Review !!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity findAllReviewOfEvent(String elasticEventId) {
        Event eventInDatabase = eventRepository.findByElasticEventId(elasticEventId);
        if (eventInDatabase != null) {
            return ResponseEntity.status(HttpStatus.OK).body(eventInDatabase);
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("response", "Not found Event");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity pushNotificationTopic(UserNotification notification, String topic) {
        Message build = Message.builder().setWebpushConfig(
                WebpushConfig.builder()
                        .setFcmOptions(WebpushFcmOptions.withLink(notification.getLinkUrl()))
                        .setNotification(
                                WebpushNotification.builder()
                                        .setTitle(notification.getTitle())
                                        .setImage(notification.getPictureUrl())
                                        .setBody(notification.getMessageDetail())
                                        .setIcon("/icon.png").build())
                        .build()).setTopic(topic).build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(build);
        } catch (FirebaseMessagingException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Successfully sent message: " + response);
        return null;
    }

    public ResponseEntity subscribeAllEventThatUserHaveTicket(String userNotification, String uid) {
        try {
            ResponseEntity<User> userResponse = restTemplate.getForEntity(USERSERVICE_URL + "/user/" + uid, User.class);
            User userBody = userResponse.getBody();
            List<UserEventTicket> userEventTicketForSubscribeList = userEventTicketRespository.findByUid(userBody.getUid());
            userEventTicketForSubscribeList.forEach((subscribeEvent) -> {
                System.out.println("Subscribe loop : " + uid + " | ElasticEventID : " + subscribeEvent.getElasticEventId());
                this.subscribeEventTopic(userNotification, subscribeEvent.getElasticEventId());
            });
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).build();
        }
    }

    public ResponseEntity pushNotificationToEventTopic(UserNotification userNotification, String elasticEventId) {
        Event eventForPushNotification = eventRepository.findByElasticEventId(elasticEventId);
        if (eventForPushNotification != null) {
            userNotification.setPictureUrl(eventForPushNotification.getEventPictureCover());
            this.pushNotificationTopic(userNotification, elasticEventId);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity getUserReviewOfEvent(String uid, String elasticEventId) {
        Review userEventReview = reviewRepository.findByUidAndElasticEventId(uid, elasticEventId);
        if (userEventReview != null) {
            userEventReview.setIsReview(true);
            return ResponseEntity.status(HttpStatus.OK).body(userEventReview);
        }
        userEventReview = new Review();
        return ResponseEntity.status(HttpStatus.OK).body(userEventReview);
    }

}
