package com.cherrysoft.crawler.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndexedWebPagesResultDTO {
  private final WebPageUrlSetDTO payload;
  private final WebPageUrlSetDTO nonIndexedUrlSet;
  private final WebPageUrlSetDTO failedToIndexUrlSet;
  private final WebPageUrlSetDTO successfullyIndexedUrlSet;
  private final WebPageUrlSetDTO alreadyIndexedUrlSet;
}
