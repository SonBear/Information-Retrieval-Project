package com.cherrysoft.solrfacade.service.filters.suggest;

import com.cherrysoft.solrfacade.model.SuggestResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SuggestQueryFilter extends QueryFilter<SuggestResult> {

  public SuggestQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SuggestResult payload) {
    payload.addSuggestions(getAllSuggestions());
    super.processQueryResult(payload);
  }

  public List<String> getAllSuggestions() {
    return response.getSuggesterResponse()
        .getSuggestedTerms().values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

}
