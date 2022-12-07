package com.cherrysoft.solrfacade.service.pipes;

import com.cherrysoft.solrfacade.model.WebPagesResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;

import static java.util.Objects.nonNull;

public class QueryPipe {
  private QueryFilter filter;

  public void send(WebPagesResult payload) {
    if (nonNull(filter)) {
      filter.processQueryResult(payload);
    }
  }

  public void setFilter(QueryFilter filter) {
    this.filter = filter;
  }

}
