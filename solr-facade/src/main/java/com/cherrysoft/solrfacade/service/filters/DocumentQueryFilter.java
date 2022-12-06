package com.cherrysoft.solrfacade.service.filters;

import com.cherrysoft.solrfacade.model.WebPageDocument;
import com.cherrysoft.solrfacade.model.WebPagesResult;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class DocumentQueryFilter extends QueryFilter {

  public DocumentQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(WebPagesResult payload) {
    payload.addWebPageDocuments(getWebPageDocuments());
    super.processQueryResult(payload);
  }

  private List<WebPageDocument> getWebPageDocuments() {
    return response.getBeans(WebPageDocument.class);
  }

}
