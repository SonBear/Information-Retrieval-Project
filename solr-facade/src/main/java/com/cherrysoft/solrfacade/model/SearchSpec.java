package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.SupportedDictionary;
import lombok.Getter;

import java.util.List;
import java.util.Map;

import static com.cherrysoft.solrfacade.util.SearchFields.ROWS_PER_PAGE;

@Getter
public class SearchSpec extends ParamSpec {
  private final String query;

  public SearchSpec(String query, Map<String, List<String>> additionalParams) {
    super(additionalParams);
    this.query = query;
  }

  public String getDictionary() {
    List<String> dictParam = params.getOrDefault("dictionary", List.of(SupportedDictionary.SPANISH.getAlias()));
    return SupportedDictionary.getNameOf(dictParam.get(0));
  }

  public int getStart() {
    List<String> pageParam = params.getOrDefault("page", List.of("1"));
    int page = Integer.parseInt(pageParam.get(0)) - 1;
    return Math.max(0, page) * ROWS_PER_PAGE;
  }

}
