package com.cherrysoft.solrfacade.service.filters;

import com.cherrysoft.solrfacade.model.WebPagesResult;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class HighlightQueryFilter extends QueryFilter {

  public HighlightQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(WebPagesResult payload) {
    payload.addHighlightSnippets(getHighlightSnippets());
    getPipe().send(payload);
  }

  private List<String> getHighlightSnippets() {
    return response.getHighlighting()
        .values().stream()
        .map(Map::values)
        .flatMap(Collection::stream)
        .flatMap(Collection::stream)
        .collect(toList());
  }

}
