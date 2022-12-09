package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.TextUtils;
import lombok.Getter;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

import static com.cherrysoft.solrfacade.util.SearchFields.*;
import static java.util.Objects.isNull;

@Getter
public class RecoveredDocument {
  private String textSpanish;
  private String textEnglish;
  private String title;
  private String url;
  private String contentType;
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

  @Field(FIELD_TITLE)
  void setTitle(List<String> titleField) {
    this.title = extractText(titleField);
  }

  @Field(FIELD_URL)
  void setUrl(List<String> urlField) {
    this.url = extractText(urlField);
  }

  @Field(FIELD_CONTENT_TYPE)
  void setContentType(List<String> contentTypeField) {
    this.contentType = extractText(contentTypeField);
  }

  private String extractText(List<String> field) {
    if (isNull(field) || field.isEmpty()) {
      return null;
    }
    return TextUtils.cleanText(field.get(0));
  }

}
