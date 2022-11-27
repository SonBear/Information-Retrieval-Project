package com.cherrysoft.crawler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.management.JMException;

@Service
@RequiredArgsConstructor
public class CrawlerService {
  private final Pipeline solrPipeline;

  public void indexUrl(String url) {
    Spider spider = Spider.create(new GenericPageProcessor())
        .addUrl(url)
        .addPipeline(solrPipeline)
        .thread(5);

    try {
      SpiderMonitor.instance().register(spider);
    } catch (JMException e) {
      throw new RuntimeException(e);
    }
    spider.run();
  }

}
