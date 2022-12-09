package com.cherrysoft.solrfacade.service.processors;

import com.cherrysoft.solrfacade.model.FacetResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import com.cherrysoft.solrfacade.service.filters.facet.FacetQueryFilter;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class FacetResultProcessor extends ResultProcessor<FacetResult> {

  public FacetResultProcessor(QueryResponse response) {
    super(response);
  }

  public static FacetResult processResult(QueryResponse response) {
    var pipeline = new FacetResultProcessor(response);
    return pipeline.processResult();
  }

  @Override
  protected FacetResult createPayload() {
    return new FacetResult();
  }

  @Override
  protected List<QueryFilter<FacetResult>> createFilters() {
    return List.of(
        new FacetQueryFilter(response)
    );
  }

}
