package com.cherrysoft.solrfacade.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class WebPagesResultDTO {
  private final List<WebPageDocumentDTO> documents;
  private final List<String> hlSnippets;
}
