package com.cherrysoft.solrfacade.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResultDTO {
  private final List<RecoveredDocumentDTO> documents;
  private final SpellcheckResultDTO spellcheckResult;

  @Data
  @Builder
  public static class SpellcheckResultDTO {
    private final List<String> suggestions;
    private final List<String> collations;
  }

}
