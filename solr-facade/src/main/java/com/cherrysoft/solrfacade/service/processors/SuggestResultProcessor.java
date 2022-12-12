package com.cherrysoft.solrfacade.service.processors;

import com.cherrysoft.solrfacade.model.SuggestResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.filters.suggest.SuggestQueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class SuggestResultProcessor extends ResultProcessor<SuggestResult> {

  public SuggestResultProcessor(QueryResponse response) {
    super(response);
  }

  public static SuggestResult processResult(QueryResponse response) {
    var pipeline = new SuggestResultProcessor(response);
    return pipeline.processResult();
  }

  @Override
  protected SuggestResult createPayload() {
    return new SuggestResult();
  }

  @Override
  protected List<QueryFilter<SuggestResult>> createFilters() {
    return List.of(
        new SuggestQueryFilter(response)
    );
  }

}
