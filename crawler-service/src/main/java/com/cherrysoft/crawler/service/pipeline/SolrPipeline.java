package com.cherrysoft.crawler.service.pipeline;

import com.cherrysoft.crawler.service.WebPageUpdaterService;
import lombok.RequiredArgsConstructor;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@RequiredArgsConstructor
public class SolrPipeline implements Pipeline {
  private final WebPageUpdaterService webPageUpdaterService;

  @Override
  public void process(ResultItems resultItems, Task task) {
    if (resultItems.getAll().isEmpty()) {
      return;
    }
    String html = resultItems.get("text");
    webPageUpdaterService.uploadWebPage(html);
  }

}
