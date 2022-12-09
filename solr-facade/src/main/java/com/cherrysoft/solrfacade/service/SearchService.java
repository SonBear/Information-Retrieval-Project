package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.model.SearchSpec;
import com.cherrysoft.solrfacade.service.processors.SearchResultProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SearchService {
  private final SolrClient solrClient;
  private final SolrQuery solrQuery;
  private SearchSpec searchSpec;

  public SearchResult search(SearchSpec searchSpec) {
    this.searchSpec = searchSpec;
    try {
      QueryResponse response = tryGetSearchResponse();
      return SearchResultProcessor.processResult(response);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return SearchResult.EMPTY;
    }
  }

  private QueryResponse tryGetSearchResponse() throws SolrServerException, IOException {
    prepareSearchQuery();
    return solrClient.query(solrQuery);
  }

  private void prepareSearchQuery() {
    solrQuery.setQuery(searchSpec.getQuery());
    solrQuery.setParam("spellcheck.dictionary", searchSpec.getDictionary());
  }

}
