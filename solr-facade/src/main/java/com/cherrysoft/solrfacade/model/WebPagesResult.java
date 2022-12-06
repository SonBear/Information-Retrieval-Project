package com.cherrysoft.solrfacade.model;

import com.cherrysoft.solrfacade.util.TextUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public final class WebPagesResult {
  public static final WebPagesResult EMPTY = new WebPagesResult();
  private final List<WebPageDocument> webPageDocuments;
  private List<String> highlightSnippets;

  public SearchWebPageResult() {
    this(List.of(), List.of());
  }

  public SearchWebPageResult(List<WebPageDocument> webPageDocuments, List<String> hlSnippets) {
    this.webPageDocuments = webPageDocuments;
    addHighlightSnippets(hlSnippets);
  }

  public void addHighlightSnippets(List<String> snippets) {
    snippets.forEach(this::addHighlightSnippet);
  }

  public void addHighlightSnippet(String snippet) {
    if (isNull(highlightSnippets)) {
      highlightSnippets = new ArrayList<>();
    }
    highlightSnippets.add(TextUtils.cleanText(snippet));
  }

}
