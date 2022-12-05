package com.cherrysoft.crawler.service.http;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

@Component
public class StatusCodeAwareHttpClientDownloader extends HttpClientDownloader {

  @Override
  public Page download(Request request, Task task) {
    Page downloadedPage = super.download(request, task);
    int statusCode = downloadedPage.getStatusCode();
    boolean successStatusCode = statusCode >= 200 && statusCode <= 299;
    request.putExtra("crawlFailed", !successStatusCode);
    request.putExtra("statusCode", statusCode);
    return downloadedPage;
  }

}
