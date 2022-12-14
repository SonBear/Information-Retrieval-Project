package com.cherrysoft.solrfacade.util;

public class FacetFields {
  public static final String LANGUAGE_FF_WITH_EX_TAG = "{!ex=tagForLanguage}facet_language";
  public static final String LANGUAGE_FQ_TAG = "{!tag='tagForLanguage'}";

  public static final String DOCUMENT_TYPE_FF_EX_WITH_TAG = "{!ex=tagForDocumentType}document_type";
  public static final String DOCUMENT_TYPE_FQ_TAG = "{!tag='tagForDocumentType'}";
}
