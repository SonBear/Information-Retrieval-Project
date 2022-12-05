package com.cherrysoft.crawler.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndexedWebPagesResult {
  private final WebPageUrlSet urlSetPayload;
  private final WebPageUrlSet nonIndexedUrlSet;
  private final WebPageUrlSet failedToIndexUrlSet;

  public WebPageUrlSet getSuccessfullyIndexedUrlSet() {
    return urlSetPayload
        .asymmetricDiff(failedToIndexUrlSet)
        .asymmetricDiff(getAlreadyIndexedUrlSet());
  }

  public WebPageUrlSet getAlreadyIndexedUrlSet() {
    return urlSetPayload.asymmetricDiff(nonIndexedUrlSet);
  }

}
