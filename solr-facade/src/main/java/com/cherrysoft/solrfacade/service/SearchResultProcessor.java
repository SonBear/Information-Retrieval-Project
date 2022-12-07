package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.RecoveredDocumentsQueryFilter;
import com.cherrysoft.solrfacade.service.filters.HighlightQueryFilter;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.filters.SpellcheckQueryFilter;
import com.cherrysoft.solrfacade.service.pipes.QueryPipe;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

@RequiredArgsConstructor
public class SearchResultProcessor {
  private final QueryResponse response;
  private QueryPipe firstPipe;

  public static SearchResult processResult(QueryResponse response) {
    var pipeline = new SearchResultProcessor(response);
    return pipeline.processResult();
  }

  public SearchResult processResult() {
    SearchResult payload = new SearchResult();
    buildPipeline();
    sendThroughPipeline(payload);
    return payload;
  }

  private void buildPipeline() {
    List<QueryFilter> filters = createFilters();
    QueryPipe aPipe = new QueryPipe();
    firstPipe = aPipe;
    for (QueryFilter filter : filters) {
      aPipe.setFilter(filter);
      aPipe = new QueryPipe();
      filter.setPipe(aPipe);
    }
  }

  private List<QueryFilter> createFilters() {
    return List.of(
        new RecoveredDocumentsQueryFilter(response),
        new HighlightQueryFilter(response),
        new SpellcheckQueryFilter(response)
    );
  }

  private void sendThroughPipeline(SearchResult payload) {
    firstPipe.send(payload);
  }

}
