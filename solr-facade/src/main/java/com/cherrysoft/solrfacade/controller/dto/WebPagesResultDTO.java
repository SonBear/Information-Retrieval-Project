package com.cherrysoft.solrfacade.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WebPagesResultDTO {
  private final List<WebPageDocumentDTO> documents;
  private final SpellcheckResultDTO spellcheckResult;
  private final List<String> hlSnippets;

  @Data
  @Builder
  public static class SpellcheckResultDTO {
    private final List<String> suggestions;
    private final List<String> collations;
  }

}
