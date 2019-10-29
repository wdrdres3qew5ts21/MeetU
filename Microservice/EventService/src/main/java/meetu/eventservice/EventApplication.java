package meetu.eventservice;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class EventApplication {

    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(EventApplication.class, args);
    }

}
