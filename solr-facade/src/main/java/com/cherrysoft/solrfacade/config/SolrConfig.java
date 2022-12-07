package com.cherrysoft.solrfacade.config;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cherrysoft.solrfacade.util.DictionaryConstants.DICTIONARY_SPANISH;
import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_ENGLISH;
import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_SPANISH;

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
    query.setParam("qf", FIELD_TEXT_SPANISH + " " + FIELD_TEXT_ENGLISH);
    query.setParam("fl", "*", "score");
    query.setParam("wt", "json");

    // Highlighting
    query.setParam("hl", "true");
    query.setParam("hl.snippets", "3");
    query.setParam("hl.fl", FIELD_TEXT_SPANISH, FIELD_TEXT_ENGLISH);

    // Spellcheck
    query.setParam("spellcheck.dictionary", DICTIONARY_SPANISH.getName());

    return query;
  }

}
