package com.cherrysoft.solrfacade.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class SearchResult {
  public static final SearchResult EMPTY = new SearchResult();
  private final List<RecoveredDocument> recoveredDocuments;
  private SpellcheckResult spellcheckResult;

  public SearchResult() {
    this.recoveredDocuments = new ArrayList<>();
  }

  public void addWebPageDocuments(List<RecoveredDocument> documents) {
    recoveredDocuments.addAll(documents);
  }

  @Getter
  @Builder
  @RequiredArgsConstructor
  public static class SpellcheckResult {
    private final List<String> suggestions;
    private final List<String> collations;
  }

}
