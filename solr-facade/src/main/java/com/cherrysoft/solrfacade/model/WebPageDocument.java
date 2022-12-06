package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.TextUtils;
import lombok.Getter;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_ENGLISH;
import static com.cherrysoft.solrfacade.util.FieldConstants.FIELD_TEXT_SPANISH;
import static java.util.Objects.isNull;

@Getter
public class WebPageDocument {
  private String textSpanish;
  private String textEnglish;
  @Field private String language;
  @Field private float score;

  @Field(FIELD_TEXT_ENGLISH)
  void setTextEnglish(List<String> englishField) {
    this.textEnglish = extractText(englishField);
  }

  @Field(FIELD_TEXT_SPANISH)
  void setTextSpanish(List<String> spanishField) {
    this.textSpanish = extractText(spanishField);
  }

  private String extractText(List<String> field) {
    if (isNull(field) || field.isEmpty()) {
      return null;
    }
    return TextUtils.cleanText(field.get(0));
  }

}
