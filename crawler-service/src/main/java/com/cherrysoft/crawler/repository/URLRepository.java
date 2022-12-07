package com.cherrysoft.crawler.repository;

import com.cherrysoft.crawler.model.WebPageUrlSet;

public interface URLRepository {

  void mergeAndSaveUrls(WebPageUrlSet urlList);

  WebPageUrlSet getUnsavedUrls(WebPageUrlSet urlSet);

  WebPageUrlSet getSavedUrls();

  WebPageUrlSet deleteUrls(WebPageUrlSet urlSet);

}
