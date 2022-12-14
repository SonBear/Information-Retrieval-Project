package com.cherrysoft.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CrawlerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrawlerServiceApplication.class, args);
  }

}
