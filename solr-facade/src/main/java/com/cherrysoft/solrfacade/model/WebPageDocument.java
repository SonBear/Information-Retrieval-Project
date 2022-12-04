package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.TextUtils;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_ENGLISH;
import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_SPANISH;
import static java.util.Objects.isNull;

public class WebPageDocument {
  @Field(FIELD_TEXT_SPANISH)
  private List<String> spanishField;

  @Field(FIELD_TEXT_ENGLISH)
  private List<String> englishField;

  @Field("language")
  private String language;

  public String getTextSpanish() {
    return extractText(spanishField);
  }

  public String getTextEnglish() {
    return extractText(englishField);
  }

  private String extractText(List<String> field) {
    if (isNull(field) || field.isEmpty()) {
      return null;
    }
    return TextUtils.cleanText(field.get(0));
  }

  public String getLanguage() {
    return language;
  }

}
