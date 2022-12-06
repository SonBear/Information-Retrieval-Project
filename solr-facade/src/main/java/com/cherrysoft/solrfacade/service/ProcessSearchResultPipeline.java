package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.WebPagesResult;
import com.cherrysoft.solrfacade.service.filters.DocumentQueryFilter;
import com.cherrysoft.solrfacade.service.filters.HighlightQueryFilter;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.filters.SpellcheckQueryFilter;
import com.cherrysoft.solrfacade.service.pipes.QueryPipe;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

@RequiredArgsConstructor
public class ProcessSearchResultPipeline {
  private final QueryResponse response;
  private QueryPipe firstPipe;

  public static WebPagesResult process(QueryResponse response) {
    var pipeline = new ProcessSearchResultPipeline(response);
    return pipeline.process();
  }

  public WebPagesResult process() {
    WebPagesResult payload = new WebPagesResult();
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
        new DocumentQueryFilter(response),
        new HighlightQueryFilter(response),
        new SpellcheckQueryFilter(response)
    );
  }

  private void sendThroughPipeline(WebPagesResult payload) {
    firstPipe.send(payload);
  }

}
