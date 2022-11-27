package com.cherrysoft.documentindexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {SolrAutoConfiguration.class})
@EnableEurekaClient
public class DocumentIndexerService {

  public static void main(String[] args) {
    SpringApplication.run(DocumentIndexerService.class, args);
  }

}
