package com.cherrysoft.crawler.service;

import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.springframework.http.MediaType;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

@RequiredArgsConstructor
public class SolrPipeline implements Pipeline {
  private final HttpSolrClient solrClient;
  private final ContentStreamUpdateRequest req;

  @Override
  public void process(ResultItems resultItems, Task task) {
    if (resultItems.getAll().size() == 0) {
      return;
    }
    String text = resultItems.get("text");
    req.addContentStream(new ContentStreamBase.StringStream(text, MediaType.TEXT_HTML_VALUE));
    try {
      NamedList<Object> result = solrClient.request(req);
      System.out.println("Result: " + result);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
