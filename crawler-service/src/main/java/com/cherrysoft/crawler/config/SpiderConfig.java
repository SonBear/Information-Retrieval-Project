package com.cherrysoft.crawler.config;

import com.cherrysoft.crawler.service.http.StatusCodeAwareHttpClientDownloader;
import com.cherrysoft.crawler.service.listener.SaveSuccessfullyCrawledUrlListener;
import com.cherrysoft.crawler.service.pipeline.SolrPipeline;
import com.cherrysoft.crawler.service.processor.GenericWebPageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SpiderConfig {
  private final SaveSuccessfullyCrawledUrlListener listener;
  private final StatusCodeAwareHttpClientDownloader downloader;

  @Bean
  public Spider defaultSpider(SolrPipeline solrPipeline) throws JMException {
    return Spider.create(new GenericWebPageProcessor())
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
