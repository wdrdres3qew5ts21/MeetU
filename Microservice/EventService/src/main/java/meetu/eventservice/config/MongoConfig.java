/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.config;

import com.mongodb.MongoClientOptions;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 *
 * @author wdrdr
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        try {
            //  mongo.setHost(host);
            //  mongo.setPort(port);
            mongo.wait(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MongoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        mongo.setMongoClientOptions(MongoClientOptions.builder()
                .retryWrites(true)
                .socketTimeout(10000)
                .build());
        return mongo;
    }

}