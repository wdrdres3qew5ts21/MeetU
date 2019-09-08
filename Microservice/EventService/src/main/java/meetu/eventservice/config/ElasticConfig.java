/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Node;
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
    
    private static final String serviceName = "es";
    private static final String region = "ap-southeast-1";
    private static final String aesEndpoint = "search-meetu-yuaodhycqphhgutbr327txd3ke.ap-southeast-1.es.amazonaws.com";

    static final AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        System.out.println("Starting Configuration");
       //  RestHighLevelClient client = this.aesClient();   //ต่อกับ AWS เข้าหรัสด้วย AES
        RestHighLevelClient client = this.restHighLevelClientVanila(); // ต่อกับ Local คอมเราเอง
        System.out.println(client);
        try {
            System.out.println(client.info(RequestOptions.DEFAULT).getNodeName());
            System.out.println(client.info(RequestOptions.DEFAULT).getClusterUuid());
        } catch (IOException ex) {
            Logger.getLogger(ElasticConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

    //@Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClientVanila() {
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
    
    public static RestHighLevelClient aesClient() {
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(serviceName);
        signer.setRegionName(region);
        System.out.println(credentialsProvider.getCredentials().getAWSAccessKeyId());
        System.out.println(credentialsProvider.getCredentials().getAWSSecretKey());
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(aesEndpoint)).setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
    }

}
