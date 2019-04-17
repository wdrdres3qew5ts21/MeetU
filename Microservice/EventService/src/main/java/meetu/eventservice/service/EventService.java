/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.eventservice.config.ElasticUtil;
import meetu.eventservice.model.Event;
import meetu.eventservice.repository.EventRepository;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestHighLevelClient elasticClient;

    private final String eventsIndex = "events";

    private final String indexType = "_doc";

    public Event createEvent(Event event) {
        Date currentDate = new Date();
        event.setCreateEventDate(currentDate);
        IndexRequest indexRequest = new IndexRequest(eventsIndex);
        String elasticEventid = null;
        Map<String, Object> pojoToMap = ElasticUtil.pojoToMap(event);
        pojoToMap.remove("organize");
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

        return savedEventMongoDB;
    }

    public List<Event> findByEventDetailInElastic(String[] eventTags, boolean isRecently, String eventDetail) {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(eventsIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchResponse searchResponse = null;
        if (eventDetail != null) {
            System.out.println("no evenTag ! : " + eventTags);
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("eventDetail", eventDetail);
            searchSourceBuilder.query(matchQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            try {
                searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (eventTags != null) {
            System.out.println("eventTag : " + eventTags);
            System.out.println(eventDetail);
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.must(
                    (eventDetail != null) ? QueryBuilders.matchQuery("eventDetail", eventDetail) : QueryBuilders.matchAllQuery()
            );
            boolQueryBuilder.filter(QueryBuilders.termsQuery("eventTags", eventTags));
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            try {
                searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (isRecently == true) {
            try {
                System.out.println("ffdsfsd");
                MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
                searchSourceBuilder.query(matchAllQueryBuilder);
                searchSourceBuilder.sort("createEventDate", SortOrder.DESC);
                searchRequest.source(searchSourceBuilder);
                searchRequest.source(searchSourceBuilder);
                searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
                SearchHits hits = searchResponse.getHits();
                eventList = ElasticUtil.searchHitsToList(hits, Event.class);
                return eventList;
            } catch (IOException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SearchHits hits = searchResponse.getHits();
        eventList = ElasticUtil.searchHitsToList(hits, Event.class);
        return eventList;
    }

    public List<Event> findAllEventsInElastic() throws IOException {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(eventsIndex);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        eventList = ElasticUtil.searchHitsToList(hits, Event.class);

        return eventList;
    }

//    public SearchSourceBuilder filterByEventTags(SearchSourceBuilder searchSourceBuilder, String eventTags[]) {
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        boolQueryBuilder.must(
//                (eventDetail != null) ? QueryBuilders.matchQuery("eventDetail", eventDetail) : QueryBuilders.matchAllQuery()
//        );
//        boolQueryBuilder.filter(QueryBuilders.termsQuery("eventTags", eventTags));
//        searchSourceBuilder.query(boolQueryBuilder);
//        searchRequest.source(searchSourceBuilder);
//        try {
//            searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
//        } catch (IOException ex) {
//            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public List<Event> findRecentlyEventsInElastic() throws IOException {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(eventsIndex);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.sort("createEventDate", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        eventList = ElasticUtil.searchHitsToList(hits, Event.class);
        return eventList;
    }

}
