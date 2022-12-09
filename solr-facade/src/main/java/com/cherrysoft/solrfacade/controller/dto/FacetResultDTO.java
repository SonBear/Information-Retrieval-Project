package com.cherrysoft.solrfacade.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FacetResultDTO {
  private final List<FacetedItemDTO> documentType;
  private final List<FacetedItemDTO> language;

  @Data
  public static class FacetedItemDTO {
    private final String classifier;
    private final long count;
  }

}
