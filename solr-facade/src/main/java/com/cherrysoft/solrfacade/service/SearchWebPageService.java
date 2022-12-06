package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.SearchWebPageResult;
import com.cherrysoft.solrfacade.model.WebPageDocument;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SearchWebPageService {
  private final SolrClient solrClient;
  private final SolrQuery solrQuery;
  private String query;

  public WebPagesResult search(String query) {
    this.query = query;
    try {
      QueryResponse response = tryGetSearchResponse(query);
      List<WebPageDocument> documents = getWebPageDocuments(response);
      List<String> hlSnippets = getHighlightSnippets(response);
      return new SearchWebPageResult(documents, hlSnippets);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return SearchWebPageResult.EMPTY;
    }
  }

  private QueryResponse tryGetSearchResponse(String query) throws SolrServerException, IOException {
    solrQuery.setQuery(query);
    return solrClient.query(solrQuery);
  }

  private List<WebPageDocument> getWebPageDocuments(QueryResponse response) {
    return response.getBeans(WebPageDocument.class);
  }

  private List<String> getHighlightSnippets(QueryResponse response) {
    return response.getHighlighting()
        .values().stream()
        .map(Map::values)
        .flatMap(Collection::stream)
        .flatMap(Collection::stream)
        .collect(toList());
  }

}
