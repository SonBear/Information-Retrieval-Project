package com.cherrysoft.crawler.service.listener;

import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.repository.URLRepository;
import com.cherrysoft.crawler.utils.URLUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

@Component
@RequiredArgsConstructor
public class SaveSuccessfullyCrawledUrlListener implements SpiderListener {
  private final URLRepository urlRepository;
  private String url;

  @Override
  public void onSuccess(Request request) {
    url = request.getUrl();
    if (request.getExtra("crawlFailed")) {
      throw new RuntimeException("Something bad happened while indexing: " + url);
    }
    saveCrawledUrl();
  }

  private void saveCrawledUrl() {
    String decodedUrl = URLUtils.decodeUrl(url);
    urlRepository.mergeAndSaveUrls(WebPageUrlSet.of(decodedUrl));
  }

}
