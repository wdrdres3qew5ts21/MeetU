/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.elasticsearch.action.search.SearchRequest;
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

    public static Map<String, Object> pojoToMap(Object objectParesedToMap) {
        Map<String, Object> convertValue = objectMapper.convertValue(objectParesedToMap, Map.class);
        ArrayList<String> removedKeyList = new ArrayList();
        convertValue.forEach((key, value) -> {
            if (value == null) {
                removedKeyList.add((String) key);
            }
        });
        removedKeyList.forEach((removedKey) -> {
            convertValue.remove(removedKey);
        });

        return convertValue;
    }

}
