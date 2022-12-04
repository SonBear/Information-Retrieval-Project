package com.cherrysoft.solrfacade.model;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

import static java.util.Objects.isNull;

public class WebPageDocument {
  @Field("attr_text_spanish")
  private List<String> textSpanish;

  @Field("attr_text_english")
  private List<String> textEnglish;

  @Field("language")
  private String language;

  public String getTextSpanish() {
    if (isNull(textSpanish) || textSpanish.isEmpty()) {
      return null;
    }
    return clearText(textSpanish.get(0));
  }

  public String getTextEnglish() {
    if (isNull(textEnglish) || textEnglish.isEmpty()) {
      return null;
    }
    return clearText(textEnglish.get(0));
  }

  private String clearText(String text) {
    String withoutBreakLines = removeBreakLines(text);
    return withoutBreakLines.trim();
  }

  private String removeBreakLines(String text) {
    return text
        .replace("\n", "")
        .replace("\r", "")
        .replace("\t", "");
  }

  public String getLanguage() {
    return language;
  }

}
