package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.WebPagesResult;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SearchWebPageService {
  private final SolrClient solrClient;
  private final SolrQuery solrQuery;
  private SearchSpec searchSpec;

  public WebPagesResult search(SearchSpec searchSpec) {
    this.searchSpec = searchSpec;
    try {
      QueryResponse response = tryGetSearchResponse();
      return getSearchResultFrom(response);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return WebPagesResult.EMPTY;
    }
  }

  private QueryResponse tryGetSearchResponse() throws SolrServerException, IOException {
    solrQuery.setQuery(searchSpec.getQuery());
    solrQuery.setParam("spellcheck.dictionary", searchSpec.getDictionary());
    return solrClient.query(solrQuery);
  }

  private WebPagesResult getSearchResultFrom(QueryResponse response) {
    return ProcessSearchResultPipeline.process(response);
  }

}
