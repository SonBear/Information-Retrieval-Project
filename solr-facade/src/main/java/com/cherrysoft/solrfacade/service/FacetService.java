package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.FacetResult;
import com.cherrysoft.solrfacade.model.FacetSpec;
import com.cherrysoft.solrfacade.service.processors.FacetResultProcessor;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.cherrysoft.solrfacade.util.FacetFields.DOCUMENT_TYPE_FQ_WITH_TAG;
import static com.cherrysoft.solrfacade.util.FacetFields.LANGUAGE_FQ_WITH_TAG;

@Service
public class FacetService {
  private final HttpSolrClient solrClient;
  private final SolrQuery solrQuery;
  private FacetSpec facetSpec;

  public FacetService(
      HttpSolrClient solrClient,
      @Qualifier("facet") SolrQuery solrQuery
  ) {
    this.solrClient = solrClient;
    this.solrQuery = solrQuery;
  }

  public FacetResult getFacets(FacetSpec facetSpec) {
    this.facetSpec = facetSpec;
    try {
      QueryResponse response = tryGetFacets();
      return FacetResultProcessor.processResult(response);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return FacetResult.EMPTY;
    }
  }

  private QueryResponse tryGetFacets() throws SolrServerException, IOException {
    prepareFacetQuery();
    return solrClient.query(solrQuery);
  }

  private void prepareFacetQuery() {
    String fqDocumentType = createFqForDocumentType();
    String fqLanguage = createFqForLanguage();
    solrQuery.setFilterQueries(fqDocumentType, fqLanguage);
  }

  private String createFqForDocumentType() {
    return createFilterQuery(DOCUMENT_TYPE_FQ_WITH_TAG, facetSpec.getDocumentTypesAsString());
  }

  private String createFqForLanguage() {
    return createFilterQuery(LANGUAGE_FQ_WITH_TAG, facetSpec.getLanguagesAsString());
  }

  private String createFilterQuery(String field, String values) {
    if (values.isBlank()) {
      return values;
    }
    return String.format("%s:(%s)", field, values);
  }

}
