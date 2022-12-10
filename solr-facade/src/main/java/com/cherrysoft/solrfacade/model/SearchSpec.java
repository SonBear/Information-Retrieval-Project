package com.cherrysoft.solrfacade.model;

import lombok.Getter;

import java.util.List;
import java.util.Map;

import static com.cherrysoft.solrfacade.util.DictionaryConstants.AVAILABLE_DICTIONARIES;
import static com.cherrysoft.solrfacade.util.DictionaryConstants.DICTIONARY_SPANISH;

@Getter
public class SearchSpec extends ParamSpec {
  private final String query;

  public SearchSpec(String query, Map<String, List<String>> additionalParams) {
    super(additionalParams);
    this.query = query;
  }

  public String getDictionary() {
    List<String> dictionary = params.getOrDefault("dictionary", List.of(DICTIONARY_SPANISH.getAlias()));
    if (!dictionary.isEmpty()) {
      return AVAILABLE_DICTIONARIES.get(dictionary.get(0));
    }
    return DICTIONARY_SPANISH.getAlias();
  }

}
