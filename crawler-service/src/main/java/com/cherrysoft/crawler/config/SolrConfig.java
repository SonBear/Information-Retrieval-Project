package com.cherrysoft.crawler.config;

import com.cherrysoft.crawler.service.SolrPipeline;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
  @Value("${solr.baseUrl}")
  private String baseSolrUrl;

  @Bean
  public SolrPipeline solrPipeline() {
    return new SolrPipeline(solrClient(), contentStreamUpdateRequest());
  }

  @Bean
  public HttpSolrClient solrClient() {
    return new HttpSolrClient.Builder()
        .withBaseSolrUrl(baseSolrUrl)
        .withConnectionTimeout(10000)
        .withSocketTimeout(6000)
        .build();
  }

  @Bean
  public ContentStreamUpdateRequest contentStreamUpdateRequest() {
    ContentStreamUpdateRequest request = new ContentStreamUpdateRequest("/update/extract");
    request.setParams(defaultSolrParams());
    return request;
  }

  @Bean
  public ModifiableSolrParams defaultSolrParams() {
    ModifiableSolrParams defaultParams = new ModifiableSolrParams();
    defaultParams.add("commit", "true");
    return defaultParams;
  }

}
