package com.cherrysoft.solrfacade.service.filters.search;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class SpellcheckQueryFilter extends QueryFilter<SearchResult> {
  private SpellCheckResponse spellCheckResponse;

  public SpellcheckQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SearchResult payload) {
    spellCheckResponse = response.getSpellCheckResponse();
    SearchResult.SpellcheckResult spellcheckResult = SearchResult.SpellcheckResult.builder()
        .suggestions(getSuggestions())
        .collations(getCollations())
        .build();

    payload.setSpellcheckResult(spellcheckResult);
    super.processQueryResult(payload);
  }

  private List<String> getSuggestions() {
    if (isNullSpellcheckResponse()) {
      return List.of();
    }
    return spellCheckResponse
        .getSuggestions().stream()
        .map(SpellCheckResponse.Suggestion::getAlternatives)
        .flatMap(Collection::stream)
        .collect(toList());
  }

  private List<String> getCollations() {
    if (isNullSpellcheckResponse()) {
      return List.of();
    }
    return spellCheckResponse
        .getCollatedResults().stream()
        .map(SpellCheckResponse.Collation::getCollationQueryString)
        .collect(toList());
  }

  private boolean isNullSpellcheckResponse() {
    return isNull(spellCheckResponse);
  }

}
