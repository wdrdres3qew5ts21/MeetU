/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.eventservice.elastic.model.ElasticEvent;
import meetu.eventservice.model.Event;
import meetu.eventservice.repository.EventRepository;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
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
    private RestHighLevelClient restHighLevelClient;

    public Event createEvent(Event event) {
        Date currentDate = new Date();
        event.setCreateEventDate(currentDate);
        ElasticEvent elasticEvent = new ElasticEvent();
        elasticEvent.setEventStartDate(currentDate);
        elasticEvent.setEventName(event.getEventName());
        elasticEvent.setEventTags(event.getEventTags());
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findByEventDetailLike(String eventDetail) {
        return eventRepository.findByEventDetailLike(eventDetail);
    }

    public List<Event> findAllElastic() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
//        searchRequest.types("_doc");
//        searchRequest.indices("events");
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().totalHits);
        ClusterHealthRequest request = new ClusterHealthRequest();
        request.indices("events");
        return null;
    }

    private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }

}
