package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.TextUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class WebPagesResult {
  public static final WebPagesResult EMPTY = new WebPagesResult();
  private final List<WebPageDocument> webPageDocuments;
  private final List<String> highlightSnippets;

  public WebPagesResult() {
    this.webPageDocuments = new ArrayList<>();
    this.highlightSnippets = new ArrayList<>();
  }

  public void addWebPageDocuments(List<WebPageDocument> documents) {
    webPageDocuments.addAll(documents);
  }

  public void addHighlightSnippets(List<String> snippets) {
    snippets.forEach(this::addHighlightSnippet);
  }

  private void addHighlightSnippet(String snippet) {
    highlightSnippets.add(TextUtils.cleanText(snippet));
  }

}
