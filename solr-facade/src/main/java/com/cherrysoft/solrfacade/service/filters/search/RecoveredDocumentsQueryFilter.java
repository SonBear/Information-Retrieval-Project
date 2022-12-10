package com.cherrysoft.solrfacade.service.filters.search;

import com.cherrysoft.solrfacade.model.RecoveredDocument;
import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class RecoveredDocumentsQueryFilter extends QueryFilter<SearchResult> {

  public RecoveredDocumentsQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(SearchResult payload) {
    payload.addWebPageDocuments(getRecoveredDocuments());
    super.processQueryResult(payload);
  }

  private List<RecoveredDocument> getRecoveredDocuments() {
    return response.getBeans(RecoveredDocument.class);
  }

}
