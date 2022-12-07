package com.cherrysoft.crawler.service;

import com.cherrysoft.crawler.model.WebPageUrlSet;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WebPageUpdaterService {
  private final HttpSolrClient solrClient;
  private final ModifiableSolrParams updateRequestParams;

  public void uploadWebPage(String html) {
    try {
      tryUploadWebPage(html);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void tryUploadWebPage(String html) throws SolrServerException, IOException {
    var request = createExtractHtmlRequest();
    request.addContentStream(createHtmlStringStream(html));
    NamedList<Object> result = solrClient.request(request);
    System.out.println("Result: " + result);
  }

  public void deleteWebPages(WebPageUrlSet urlSet) {
    try {
      tryDeleteWebPages(urlSet);
    } catch (SolrServerException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void tryDeleteWebPages(WebPageUrlSet urlSet) throws SolrServerException, IOException {
    for (String url : urlSet.getUrls()) {
      String escapedUrl = ClientUtils.escapeQueryChars(url);
      solrClient.deleteByQuery(String.format("attr_url:%s", escapedUrl));
    }
    solrClient.commit();
  }

  private ContentStreamUpdateRequest createExtractHtmlRequest() {
    var request = new ContentStreamUpdateRequest("/update/extract");
    request.setParams(updateRequestParams);
    return request;
  }

  private ContentStreamBase.StringStream createHtmlStringStream(String html) {
    return new ContentStreamBase.StringStream(html, MediaType.TEXT_HTML_VALUE);
  }

}
