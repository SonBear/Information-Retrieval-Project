package com.cherrysoft.solrfacade.config;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
  @Value("${solr.baseUrl}")
  private String baseSolrUrl;

  @Bean
  public HttpSolrClient solrClient() {
    return new HttpSolrClient.Builder()
        .withBaseSolrUrl(baseSolrUrl)
        .withConnectionTimeout(10000)
        .withSocketTimeout(6000)
        .build();
  }

  @Bean
  public SolrQuery defaultSolrQuery() {
    SolrQuery query = new SolrQuery();
    query.setRequestHandler("/select");
    query.setParam("defType", "edismax");
    query.setParam("qf", "attr_text_spanish attr_text_english");
    query.setParam("fl", "*", "score");
    query.setParam("wt", "json");
    return query;
  }

}
