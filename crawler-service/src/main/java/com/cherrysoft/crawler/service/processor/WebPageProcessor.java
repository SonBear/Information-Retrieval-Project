package com.cherrysoft.crawler.service.processor;

import org.jsoup.nodes.Attribute;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class WebPageProcessor implements PageProcessor {
  private final List<String> unnecessaryTags = List.of("style", "script", "link", "input", "form", "button");
  private Html html;

  @Override
  public void process(Page page) {
    html = page.getHtml();
    removeUnnecessaryTags();
    removeAllTagAttrsInsideBody();
    attachUrlMetaTag(page.getUrl().toString());
    String result = removeAnchorTagsPreservingInnerContent();
    page.putField("text", result);
  }

  private void removeUnnecessaryTags() {
    String cssQuery = String.join(",", unnecessaryTags);
    html.getDocument().select(cssQuery).remove();
  }

  private void removeAllTagAttrsInsideBody() {
    html.getDocument().body().getAllElements()
        .forEach(e -> e.attributes()
            .asList().stream()
            .map(Attribute::getKey)
            .forEach(e::removeAttr)
        );
  }

  private void attachUrlMetaTag(String url) {
    String urlMetaTag = String.format("<meta property='url' content='%s'>", url);
    html.getDocument().head().append(urlMetaTag);
  }

  private String removeAnchorTagsPreservingInnerContent() {
    String htmlText = html.toString();
    return htmlText.replaceAll("<\\/?\\s*a.*?>", " ");
  }

}
