package com.cherrysoft.crawler.service;

import com.cherrysoft.crawler.service.http.StatusCodeAwareHttpClientDownloader;
import com.cherrysoft.crawler.service.listener.SaveSuccessfullyCrawledUrlListener;
import com.cherrysoft.crawler.service.pipeline.SolrPipeline;
import com.cherrysoft.crawler.service.processor.WebPageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpiderFactory {
  private final SolrPipeline solrPipeline;
  private final SaveSuccessfullyCrawledUrlListener listener;
  private final StatusCodeAwareHttpClientDownloader downloader;

  public Spider createSpider() {
    return Spider.create(new WebPageProcessor())
        .addPipeline(solrPipeline)
        .setDownloader(downloader)
        .setSpiderListeners(spiderListeners())
        .thread(5);
  }

  public List<SpiderListener> spiderListeners() {
    List<SpiderListener> spiderListeners = new ArrayList<>();
    spiderListeners.add(listener);
    return spiderListeners;
  }

}
