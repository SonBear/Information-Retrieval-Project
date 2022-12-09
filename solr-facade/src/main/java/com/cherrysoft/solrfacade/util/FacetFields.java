package com.cherrysoft.solrfacade.util;

public class FacetFields {
  public static final String LANGUAGE_FF_WITH_EX_TAG = "{!ex=tagForLanguage}language";
  public static final String LANGUAGE_FQ_WITH_TAG = "{!tag='tagForLanguage'}language";

  public static final String DOCUMENT_TYPE_FF_EX_WITH_TAG = "{!ex=tagForDocumentType}document_type";
  public static final String DOCUMENT_TYPE_FQ_WITH_TAG = "{!tag='tagForDocumentType'}document_type";
}
