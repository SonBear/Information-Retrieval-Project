package com.cherrysoft.solrfacade.model;

import lombok.Data;

import java.util.Collection;
import java.util.Set;

import static java.util.Objects.isNull;

@Data
public class FacetSpec {
  private final Set<String> documentTypes;
  private final Set<String> languages;

  public String getDocumentTypesAsString() {
    return joinCollectionBySpace(documentTypes);
  }

  public String getLanguagesAsString() {
    return joinCollectionBySpace(languages);
  }

  private String joinCollectionBySpace(Collection<String> list) {
    if (isNull(list)) {
      return "";
    }
    return String.join(" ", list);
  }

}
