package com.cherrysoft.solrfacade.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResultDTO {
  private final List<String> suggestions;
}
