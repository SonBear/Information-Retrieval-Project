package com.cherrysoft.solrfacade.config;

import com.cherrysoft.solrfacade.util.SupportedDictionary;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.cherrysoft.solrfacade.util.FacetFields.DOCUMENT_TYPE_FF_EX_WITH_TAG;
import static com.cherrysoft.solrfacade.util.FacetFields.LANGUAGE_FF_WITH_EX_TAG;
import static com.cherrysoft.solrfacade.util.SearchFields.*;

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
  @Primary
  public SolrQuery searchSolrQuery() {
    SolrQuery query = createSolrQueryWithBaseParams();
    query.setParam("defType", "edismax");
    query.setParam("qf", FIELD_TEXT_SPANISH + " " + FIELD_TEXT_ENGLISH + " " + FIELD_TITLE);
    query.setParam("fl", "*", "score");

    // Highlighting
    query.setParam("hl", "true");
    query.setParam("hl.snippets", "3");
    query.setParam("hl.fl", FIELD_TEXT_SPANISH, FIELD_TEXT_ENGLISH);

    // Spellcheck
    query.setParam("spellcheck.dictionary", SupportedDictionary.SPANISH.getName());

    return query;
  }

  @Bean
  @Qualifier("facet")
  public SolrQuery facetSolrQuery() {
    SolrQuery query = createSolrQueryWithBaseParams();
    query.setParam("facet", "true");
    query.setParam("facet.mincount", "0");
    query.addFacetField(DOCUMENT_TYPE_FF_EX_WITH_TAG, LANGUAGE_FF_WITH_EX_TAG);
    query.setParam("fl", "score");
    query.setParam("q", "*:*");
    return query;
  }

  @Bean
  @Qualifier("suggest")
  public SolrQuery suggestSolrQuery() {
    SolrQuery query = createSolrQueryWithBaseParams();
    query.setRequestHandler("/suggest");
    return query;
  }

  private SolrQuery createSolrQueryWithBaseParams() {
    SolrQuery query = new SolrQuery();
    query.setRequestHandler("/select");
    query.setParam("wt", "json");
    return query;
  }

}
