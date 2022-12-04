package com.cherrysoft.solrfacade.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebPageDocumentDTO {
  private final String textSpanish;
  private final String textEnglish;
  private final String language;
}
