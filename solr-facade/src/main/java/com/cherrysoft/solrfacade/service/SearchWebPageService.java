package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.WebPageDocument;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchWebPageService {
  private final SolrClient solrClient;
  private final SolrQuery solrQuery;

  public List<WebPageDocument> search(String query) {
    try {
      return tryToSearch(query);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return List.of();
    }
  }

  private List<WebPageDocument> tryToSearch(String query) throws SolrServerException, IOException {
    solrQuery.setQuery(query);
    QueryResponse result = solrClient.query(solrQuery);
    return result.getBeans(WebPageDocument.class);
  }

}
