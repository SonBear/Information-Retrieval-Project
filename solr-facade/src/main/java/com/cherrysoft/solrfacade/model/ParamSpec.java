package com.cherrysoft.solrfacade.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public abstract class ParamSpec {
  protected final Map<String, List<String>> params;

  public static Map<String, List<String>> convert(Map<String, String> params) {
    return params.entrySet().stream()
        .collect(
            toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue().split(",")))
        );
  }

  public FacetFilterQueryField getFacetFqField(SupportedFacetField field) {
    if (!params.containsKey(field.getParamName())) {
      return new FacetFilterQueryField.NullFilterQueryField();
    }
    List<String> values = params.get(field.getParamName());
    return FacetFilterQueryField.builder()
        .facetField(field)
        .values(values)
        .build();
  }

}
