package com.cherrysoft.crawler.service;

import com.cherrysoft.crawler.model.IndexedWebPagesResult;
import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.repository.URLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;

@Service
@RequiredArgsConstructor
public class CrawlerService {
  private final Spider spider;
  private boolean spiderRegistered = false;
  private final URLRepository urlRepository;

  public IndexedWebPagesResult indexUrls(WebPageUrlSet urlSetPayload) {
    WebPageUrlSet nonIndexedUrls = urlRepository.getUnsavedUrls(urlSetPayload);
    crawlAndIndexUrls(nonIndexedUrls);
    WebPageUrlSet failedToIndexUrlSet = urlRepository.getUnsavedUrls(urlSetPayload);

    return IndexedWebPagesResult.builder()
        .urlSetPayload(urlSetPayload)
        .nonIndexedUrlSet(nonIndexedUrls)
        .failedToIndexUrlSet(failedToIndexUrlSet)
        .build();
  }

  private void crawlAndIndexUrls(WebPageUrlSet urlSetPayload) {
    try {
      tryCrawlAndIndexUrls(urlSetPayload);
    } catch (JMException e) {
      throw new RuntimeException(e);
    }
  }

  private void tryCrawlAndIndexUrls(WebPageUrlSet urlSetPayload) throws JMException {
    if (urlSetPayload.isEmpty()) {
      return;
    }
    spider.addUrl(urlSetPayload.toArray());
    if (!spiderRegistered) {
      SpiderMonitor.instance().register(spider);
      spiderRegistered = true;
    }
    spider.run();
  }

}
