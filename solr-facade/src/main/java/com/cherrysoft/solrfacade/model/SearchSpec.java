package com.cherrysoft.solrfacade.model;

import lombok.Data;

import static com.cherrysoft.solrfacade.util.DictionaryConstants.AVAILABLE_DICTIONARIES;
import static com.cherrysoft.solrfacade.util.DictionaryConstants.DICTIONARY_SPANISH;
import static java.util.Objects.requireNonNullElse;

@Data
public class SearchSpec {
  private final String query;
  private final String dictionary;

  public SearchSpec(String query, String dictionary) {
    this.query = query;
    this.dictionary = requireNonNullElse(dictionary, DICTIONARY_SPANISH.getAlias());
  }

  public String getDictionary() {
    return AVAILABLE_DICTIONARIES.get(dictionary);
  }

}
