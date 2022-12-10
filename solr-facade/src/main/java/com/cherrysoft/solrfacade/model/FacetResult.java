package com.cherrysoft.solrfacade.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNullElse;

@Getter
public class FacetResult {
  public static final FacetResult EMPTY = new FacetResult();
  private final List<FacetedItem> documentType;
  private final List<FacetedItem> language;

  public FacetResult() {
    this.documentType = new ArrayList<>();
    this.language = new ArrayList<>();
  }

  public void extractFacetsFrom(Map<String, List<FacetedItem>> facets) {
    addDocumentTypeItems(facets.get(SupportedFacetField.DOCUMENT_TYPE.fieldName));
    addLanguageItems(facets.get(SupportedFacetField.LANGUAGE.fieldName));
  }

  private void addDocumentTypeItems(List<FacetedItem> items) {
    documentType.addAll(requireNonNullElse(items, List.of()));
  }

  private void addLanguageItems(List<FacetedItem> items) {
    language.addAll(requireNonNullElse(items, List.of()));
  }

  @Data
  public static class FacetedItem {
    private final String classifier;
    private final long count;
  }

}
