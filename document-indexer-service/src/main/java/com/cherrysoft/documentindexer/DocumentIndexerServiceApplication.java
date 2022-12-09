package com.cherrysoft.documentindexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DocumentIndexerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DocumentIndexerServiceApplication.class, args);
  }

}
