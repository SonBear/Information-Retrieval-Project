package com.cherrysoft.crawler.service;

import com.cherrysoft.crawler.model.WebPageUrlSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.management.JMException;

@Service
@RequiredArgsConstructor
public class CrawlerService {
  private final SpiderFactory spiderFactory;
  private Spider spider;
  private WebPageUrlSet payload;

  public void crawlAndIndexUrls(WebPageUrlSet payload) {
    this.payload = payload;
    this.spider = spiderFactory.createSpider();
    try {
      tryCrawlAndIndexUrls();
    } catch (JMException e) {
      throw new RuntimeException(e);
    }
  }

  private void tryCrawlAndIndexUrls() throws JMException {
    if (emptyPayload()) {
      return;
    }
    addPayloadToSpider();
    startSpider();
  }

  private void addPayloadToSpider() {
    spider.addUrl(payload.toArray());
  }

  private void startSpider() {
    spider.run();
  }

  private boolean emptyPayload() {
    return payload.isEmpty();
  }

}
