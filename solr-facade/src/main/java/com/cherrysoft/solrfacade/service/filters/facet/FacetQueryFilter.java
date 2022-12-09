package com.cherrysoft.solrfacade.service.filters.facet;

import com.cherrysoft.solrfacade.model.FacetResult;
import com.cherrysoft.solrfacade.service.filters.QueryFilter;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class FacetQueryFilter extends QueryFilter<FacetResult> {

  public FacetQueryFilter(QueryResponse response) {
    super(response);
  }

  @Override
  public void processQueryResult(FacetResult payload) {
    payload.extractFacetsFrom(getFacets());
    super.processQueryResult(payload);
  }

  public Map<String, List<FacetResult.FacetedItem>> getFacets() {
    return response.getFacetFields().stream()
        .collect(
            toMap(FacetField::getName, this::mapToFacetedItemList)
        );
  }

  public List<FacetResult.FacetedItem> mapToFacetedItemList(FacetField facetField) {
    return facetField.getValues().stream()
        .map(item -> new FacetResult.FacetedItem(item.getName(), item.getCount()))
        .collect(toList());
  }

}
