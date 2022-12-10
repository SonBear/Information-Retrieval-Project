package com.cherrysoft.solrfacade.service.filters.search;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpellcheckQueryFilter extends QueryFilter<SearchResult> {

  public SpellcheckQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SearchResult payload) {
    SearchResult.SpellcheckResult spellcheckResult = SearchResult.SpellcheckResult.builder()
        .suggestions(getSuggestions())
        .collations(getCollations())
        .build();

    payload.setSpellcheckResult(spellcheckResult);
    super.processQueryResult(payload);
  }

  private List<String> getSuggestions() {
    return response.getSpellCheckResponse()
        .getSuggestions().stream()
        .map(SpellCheckResponse.Suggestion::getAlternatives)
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private List<String> getCollations() {
    return response.getSpellCheckResponse()
        .getCollatedResults().stream()
        .map(SpellCheckResponse.Collation::getCollationQueryString)
        .collect(toList());
  }

}
