/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author wdrdr
 */
@Configuration
public class ElasticConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        System.out.println("Starting Configuration");
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200))
        );
        System.out.println(client);
        try {
            System.out.println(client.info(RequestOptions.DEFAULT).getNodeName());
            System.out.println(client.info(RequestOptions.DEFAULT).getClusterUuid());
            System.out.println(client.info(RequestOptions.DEFAULT).isAvailable());
//            Aggregations aggregations = searchResponse.getAggregations();
//            for (Aggregation aggregation : aggregations) {
//                System.out.println(aggregation.getMetaData());
//            }

        } catch (IOException ex) {
            Logger.getLogger(ElasticConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

}
