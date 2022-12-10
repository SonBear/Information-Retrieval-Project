package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.SupportedDictionary;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class SearchSpec extends ParamSpec {
  private final String query;

  public SearchSpec(String query, Map<String, List<String>> additionalParams) {
    super(additionalParams);
    this.query = query;
  }

  public String getDictionary() {
    List<String> dictionary = params.getOrDefault("dictionary", List.of(SupportedDictionary.SPANISH.getAlias()));
    if (!dictionary.isEmpty()) {
      return SupportedDictionary.getNameOf(dictionary.get(0));
    }
    return SupportedDictionary.SPANISH.getName();
  }

}
