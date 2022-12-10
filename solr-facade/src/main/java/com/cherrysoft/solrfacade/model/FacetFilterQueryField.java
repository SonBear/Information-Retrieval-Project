package com.cherrysoft.solrfacade.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import static java.util.Objects.requireNonNullElse;

@Data
@Builder
public class FacetFilterQueryField {
  private final String prefix;
  private final SupportedFacetField facetField;
  private final List<String> values;

  public FacetFilterQueryField(String prefix, SupportedFacetField facetField, List<String> values) {
    this.prefix = requireNonNullElse(prefix, "");
    this.facetField = facetField;
    this.values = requireNonNullElse(values, List.of());
  }

  public FacetFilterQueryField withPrefix(String prefix) {
    return FacetFilterQueryField.builder()
        .prefix(prefix)
        .facetField(facetField)
        .values(values)
        .build();
  }

  public String getFieldName() {
    return facetField.getFieldName();
  }

  public String getAsStringWithValuesJoinedBySpaces() {
    String joinedValues = String.join(" ", values);
    StringBuilder bobTheBuilder = new StringBuilder();
    bobTheBuilder
        .append(prefix)
        .append(getFieldName())
        .append(String.format(":(%s)", joinedValues));
    return bobTheBuilder.toString();
  }

  public static class NullFilterQueryField extends FacetFilterQueryField {

    public NullFilterQueryField() {
      super(null, null, null);
    }

    @Override
    public FacetFilterQueryField withPrefix(String prefix) {
      return new NullFilterQueryField();
    }

    @Override
    public String getFieldName() {
      return "";
    }

    @Override
    public String getAsStringWithValuesJoinedBySpaces() {
      return "";
    }

  }

}
