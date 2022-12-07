package com.cherrysoft.solrfacade.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecoveredDocumentDTO {
  private final String textSpanish;
  private final String textEnglish;
  private final String title;
  private final String url;
  private final String contentType;
  private final String language;
  private final float score;
}
