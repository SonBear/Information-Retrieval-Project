package com.cherrysoft.solrfacade.service.filters.search;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class HighlightQueryFilter extends QueryFilter<SearchResult> {

  public HighlightQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SearchResult payload) {
    enrichDocumentsWithHighlightSnippets(payload);
    super.processQueryResult(payload);
  }

  private void enrichDocumentsWithHighlightSnippets(SearchResult payload) {
    var snippetsPerDocument = getHighlightSnippetsPerDocument();
    payload.getRecoveredDocuments().forEach(document -> {
      List<String> documentSnippets = snippetsPerDocument.get(document.getId());
      document.addHighlightSnippets(documentSnippets);
    });
  }

  private Map<String, List<String>> getHighlightSnippetsPerDocument() {
    return response.getHighlighting().entrySet().stream()
        .collect(
            toMap(Map.Entry::getKey, e -> flatMapValuesToSingleList(e.getValue()))
        );
  }

  private List<String> flatMapValuesToSingleList(Map<String, List<String>> map) {
    return map.values().stream()
        .flatMap(Collection::stream)
        .collect(toList());
  }

}
