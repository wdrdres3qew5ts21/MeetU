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
import org.springframework.context.annotation.Scope;
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

    public List<Event> findEventByUsingFilter(String[] eventTags, boolean isRecently, String eventDetail) throws IOException {
        BoolQueryBuilder queryFilter = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(eventsIndex);
        SearchResponse searchResponse = null;

        System.out.println("--- Start Filtering ---");
        if (eventTags != null) {
            System.out.println("Event Tag");
            queryFilter = filterByEventTags(queryFilter, eventTags);
        }
        if (eventDetail != null) {
            System.out.println("Event Detail");
            queryFilter.must(filterByEventDetail(eventDetail));
        }
        if (isRecently == true) {
            System.out.println("Recently");
            searchSourceBuilder = filterByRecently(searchSourceBuilder, "createEventDate");
        }

        searchSourceBuilder.query(queryFilter);
        searchRequest.source(searchSourceBuilder);
        searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);

        return ElasticUtil.searchHitsToList(searchResponse.getHits(), Event.class);
    }

    public List<Event> findAllEventsInElastic() throws IOException {
        System.out.println("find all event");
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(eventsIndex);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);

        return ElasticUtil.searchHitsToList(searchResponse.getHits(), Event.class);
    }

    public BoolQueryBuilder filterByEventTags(BoolQueryBuilder queryFilter, String eventTags[]) {
        queryFilter.filter(QueryBuilders.termsQuery("eventTags", eventTags));
        return queryFilter;
    }

    public MatchQueryBuilder filterByEventDetail(String eventDetail) {
        MatchQueryBuilder alreadyFilterByEventDetail = QueryBuilders.matchQuery("eventDetail", eventDetail);
        return alreadyFilterByEventDetail;
    }

    public SearchSourceBuilder filterByRecently(SearchSourceBuilder searchSourceBuilder, String sortedField) throws IOException {
        searchSourceBuilder.sort(sortedField, SortOrder.DESC);
        return searchSourceBuilder;
    }

}
