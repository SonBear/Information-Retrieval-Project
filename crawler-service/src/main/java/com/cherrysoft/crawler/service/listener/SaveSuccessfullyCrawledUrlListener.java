package com.cherrysoft.crawler.service.listener;

import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.repository.URLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

@Component
@RequiredArgsConstructor
public class SaveSuccessfullyCrawledUrlListener implements SpiderListener {
  private final URLRepository urlRepository;

  @Override
  public void onSuccess(Request request) {
    if (request.getExtra("crawlFailed")) {
      throw new RuntimeException("Something bad happened while indexing: " + request.getUrl());
    }
    urlRepository.mergeAndSaveUrls(WebPageUrlSet.of(request.getUrl()));
  }

}
