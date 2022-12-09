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

  private enum SupportedFacets {
    DOCUMENT_TYPE("document_type"),
    LANGUAGE("language");

    public final String value;

    SupportedFacets(String value) {
      this.value = value;
    }

  }

  public FacetResult() {
    this.documentType = new ArrayList<>();
    this.language = new ArrayList<>();
  }

  public void extractFacetsFrom(Map<String, List<FacetedItem>> facets) {
    addDocumentTypeItems(facets.get(SupportedFacets.DOCUMENT_TYPE.value));
    addLanguageItems(facets.get(SupportedFacets.LANGUAGE.value));
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
