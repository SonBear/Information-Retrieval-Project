package com.cherrysoft.crawler.service;

import org.jsoup.nodes.Attribute;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class GenericPageProcessor implements PageProcessor {
  private Html html;

  @Override
  public void process(Page page) {
    html = page.getHtml();
    removeStyleAndScriptTags();
    removeAllTagAttrsInsideBody();
    page.putField("text", html.toString());
  }

  private void removeStyleAndScriptTags() {
    html.getDocument().select("style,script").remove();
  }

  private void removeAllTagAttrsInsideBody() {
    html.getDocument().body().getAllElements()
        .forEach(e -> e.attributes()
            .asList().stream()
            .map(Attribute::getKey)
            .forEach(e::removeAttr)
        );
  }

}
