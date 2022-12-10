package com.cherrysoft.solrfacade.service.processors;

import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.pipes.QueryPipe;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

@RequiredArgsConstructor
public abstract class ResultProcessor<T> {
  protected final QueryResponse response;
  private QueryPipe<T> firstPipe;

  private void buildPipeline() {
    List<QueryFilter<T>> filters = createFilters();
    QueryPipe<T> aPipe = new QueryPipe<>();
    firstPipe = aPipe;
    for (QueryFilter<T> filter : filters) {
      aPipe.setFilter(filter);
      aPipe = new QueryPipe<>();
      filter.setPipe(aPipe);
    }
  }

  public T processResult() {
    T payload = createPayload();
    buildPipeline();
    sendThroughPipeline(payload);
    return payload;
  }

  protected abstract T createPayload();

  protected abstract List<QueryFilter<T>> createFilters();

  protected final void sendThroughPipeline(T payload) {
    firstPipe.send(payload);
  }

}
