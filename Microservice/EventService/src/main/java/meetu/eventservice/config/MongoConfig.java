/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author wdrdr
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.port:27017}")
    private int port;

//    @Bean
//    public MongoClientFactoryBean mongo() {
//        System.out.println("Host : " + host + " Port : " + port);
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost(host);
//        mongo.setPort(port);
//        mongo.setMongoClientOptions(MongoClientOptions.builder()
//                .retryWrites(true)
//                .socketTimeout(10000)
//                .build());
//        return mongo;
//    }
    
}
