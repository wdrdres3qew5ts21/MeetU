/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 *
 * @author wdrdr
 */
public class ElasticUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ArrayList<T> searchHitsToList(SearchHits searchHits, Class<T> type) {
        ArrayList<T> elasticParseToPojo = new ArrayList();

        for (SearchHit hit : searchHits) {
            elasticParseToPojo.add(objectMapper.convertValue(hit.getSourceAsMap(), type));
        }
        return elasticParseToPojo;
    }

}
