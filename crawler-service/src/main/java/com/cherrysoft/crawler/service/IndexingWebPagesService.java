package com.cherrysoft.crawler.service;

import com.cherrysoft.crawler.model.IndexedWebPagesResult;
import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.repository.URLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexingWebPagesService {
  private final CrawlerService crawlerService;
  private final WebPageUpdaterService webPageUpdaterService;
  private final URLRepository urlRepository;

  public IndexedWebPagesResult indexUrls(WebPageUrlSet payload) {
    WebPageUrlSet nonIndexedUrls = urlRepository.getUnsavedUrls(payload);
    crawlerService.crawlAndIndexUrls(nonIndexedUrls);
    WebPageUrlSet failedToIndexUrlSet = urlRepository.getUnsavedUrls(payload);

    return IndexedWebPagesResult.builder()
        .urlSetPayload(payload)
        .nonIndexedUrlSet(nonIndexedUrls)
        .failedToIndexUrlSet(failedToIndexUrlSet)
        .build();
  }

  public IndexedWebPagesResult reindexUrls(WebPageUrlSet urlSetToReindex) {
    deleteUrls(urlSetToReindex);
    return indexUrls(urlSetToReindex);
  }

  public WebPageUrlSet getIndexedUrls() {
    return urlRepository.getSavedUrls();
  }

  public WebPageUrlSet deleteUrls(WebPageUrlSet urlSetToDelete) {
    webPageUpdaterService.deleteWebPages(urlSetToDelete);
    return urlRepository.deleteUrls(urlSetToDelete);
  }

}
