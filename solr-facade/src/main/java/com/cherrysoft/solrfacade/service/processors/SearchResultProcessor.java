package com.cherrysoft.solrfacade.service.processors;

import com.cherrysoft.solrfacade.model.SearchResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.filters.search.HighlightQueryFilter;
import com.cherrysoft.solrfacade.service.filters.search.RecoveredDocumentsQueryFilter;
import com.cherrysoft.solrfacade.service.filters.search.SpellcheckQueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class SearchResultProcessor extends ResultProcessor<SearchResult> {

  public SearchResultProcessor(QueryResponse response) {
    super(response);
  }

  public static SearchResult processResult(QueryResponse response) {
    var pipeline = new SearchResultProcessor(response);
    return pipeline.processResult();
  }

  @Override
  protected SearchResult createPayload() {
    return new SearchResult();
  }

  @Override
  protected List<QueryFilter<SearchResult>> createFilters() {
    return List.of(
        new RecoveredDocumentsQueryFilter(response),
        new HighlightQueryFilter(response),
        new SpellcheckQueryFilter(response)
    );
  }

}
