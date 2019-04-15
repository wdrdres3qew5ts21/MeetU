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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author wdrdr
 */
@Configuration
public class ElasticConfig {

    @Value("${elasticsearch.host:localhost}")
    private String host;

    @Value("${elasticsearch.port:9200}")
    private int port;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        System.out.println("Starting Configuration");
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port))
        );
        System.out.println(client);
        try {
            System.out.println(client.info(RequestOptions.DEFAULT).getNodeName());
            System.out.println(client.info(RequestOptions.DEFAULT).getClusterUuid());
        } catch (IOException ex) {
            Logger.getLogger(ElasticConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

}
