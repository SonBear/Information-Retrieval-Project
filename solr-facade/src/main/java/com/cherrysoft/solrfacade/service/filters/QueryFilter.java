package com.cherrysoft.solrfacade.service.filters;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.pipes.QueryPipe;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.solr.client.solrj.response.QueryResponse;

@Setter
@Getter
@RequiredArgsConstructor
public abstract class QueryFilter {
  protected final QueryResponse response;
  private QueryPipe pipe;

  public void processQueryResult(SearchResult payload) {
    getPipe().send(payload);
  }

}
