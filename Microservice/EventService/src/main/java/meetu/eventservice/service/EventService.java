/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
        Map<String, Object> pojoToMap = ElasticUtil.pojoToMap(event);
        pojoToMap.remove("organize");
        indexRequest.source(pojoToMap);
        try {
            IndexResponse indexResponse = elasticClient.index(indexRequest, RequestOptions.DEFAULT);
            String elasticEventid = indexResponse.getId();
            event.setElasticEventId(elasticEventid);
        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventRepository.save(event);
    }

    public List<Event> findByEventDetailInElastic(String eventDetail) {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(eventsIndex);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("eventDetail", eventDetail);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
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

}
