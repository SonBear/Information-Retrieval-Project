package com.cherrysoft.solrfacade.service.filters.search;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class HighlightQueryFilter extends QueryFilter<SearchResult> {

  public HighlightQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SearchResult payload) {
    payload.addHighlightSnippets(getHighlightSnippets());
    super.processQueryResult(payload);
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
