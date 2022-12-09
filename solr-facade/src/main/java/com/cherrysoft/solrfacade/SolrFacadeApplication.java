package com.cherrysoft.solrfacade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SolrFacadeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SolrFacadeApplication.class, args);
  }

}
