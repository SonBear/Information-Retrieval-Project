package com.cherrysoft.solrfacade.service;

import com.cherrysoft.solrfacade.model.FacetResult;
import com.cherrysoft.solrfacade.model.FacetSpec;
import com.cherrysoft.solrfacade.model.SupportedFacetField;
import com.cherrysoft.solrfacade.service.processors.FacetResultProcessor;
import com.cherrysoft.solrfacade.util.FacetFields;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    var documentType = facetSpec.getFacetFqField(SupportedFacetField.DOCUMENT_TYPE);
    return documentType
        .withPrefix(FacetFields.DOCUMENT_TYPE_FQ_TAG)
        .getAsStringWithValuesJoinedBySpaces();
  }

  private String createFqForLanguage() {
    var language = facetSpec.getFacetFqField(SupportedFacetField.LANGUAGE);
    return language
        .withPrefix(FacetFields.LANGUAGE_FQ_TAG)
        .getAsStringWithValuesJoinedBySpaces();
  }

}
