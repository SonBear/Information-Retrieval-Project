package com.cherrysoft.solrfacade.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SuggestResult {
  public static final SuggestResult EMPTY = new SuggestResult();
  private final List<String> suggestions;

  public SuggestResult() {
    suggestions = new ArrayList<>();
  }

  public void addSuggestions(List<String> suggestions) {
    this.suggestions.addAll(suggestions);
  }

}
