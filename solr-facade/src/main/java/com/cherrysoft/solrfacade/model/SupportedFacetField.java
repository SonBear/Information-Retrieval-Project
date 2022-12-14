package com.cherrysoft.solrfacade.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SupportedFacetField {
  DOCUMENT_TYPE("document_type", "documentType"),
  LANGUAGE("facet_language", "language");

  public final String fieldName;
  private final String paramName;
}
