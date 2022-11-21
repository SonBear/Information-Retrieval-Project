package com.cherrysoft.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient
public class CrawlerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrawlerServiceApplication.class, args);
  }

}
