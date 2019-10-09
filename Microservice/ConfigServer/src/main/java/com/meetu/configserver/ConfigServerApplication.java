package com.meetu.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableConfigServer
@RefreshScope
@SpringBootApplication
@CrossOrigin(origins = {
    "'www.meetu-milktea.tk",
    "localhost:3001",
    "localhost:3002",
    "localhost:3003",
    "localhost:4000",
})
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
