package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.SuggestResult;
import com.cherrysoft.solrfacade.service.processors.SuggestResultProcessor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SuggestService {
  private final SolrClient solrClient;
  private final SolrQuery suggestSolrQuery;

  public SuggestService(
      SolrClient solrClient,
      @Qualifier("suggest") SolrQuery suggestSolrQuery) {
    this.solrClient = solrClient;
    this.suggestSolrQuery = suggestSolrQuery;
  }

  public SuggestResult getSuggestions(String query) {
    try {
      QueryResponse response = tryGetSuggestions(query);
      return SuggestResultProcessor.processResult(response);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return SuggestResult.EMPTY;
    }
  }

  public QueryResponse tryGetSuggestions(String query) throws SolrServerException, IOException {
    suggestSolrQuery.setQuery(query);
    return solrClient.query(suggestSolrQuery);
  }

}
