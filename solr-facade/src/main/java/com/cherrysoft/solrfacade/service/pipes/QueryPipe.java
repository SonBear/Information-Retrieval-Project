package com.cherrysoft.solrfacade.service.pipes;

import com.cherrysoft.solrfacade.service.filters.QueryFilter;

import static java.util.Objects.nonNull;

public class QueryPipe<T> {
  private QueryFilter<T> filter;

  public void send(T payload) {
    if (nonNull(filter)) {
      filter.processQueryResult(payload);
    }
  }

  public void setFilter(QueryFilter<T> filter) {
    this.filter = filter;
  }

}
