package com.cherrysoft.crawler.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class WebPageUrlSet {
  public static final WebPageUrlSet EMPTY = new WebPageUrlSet(Set.of());
  private final Set<String> urls;

  public static WebPageUrlSet of(String... urls) {
    return new WebPageUrlSet(Set.of(urls));
  }

  public WebPageUrlSet() {
    this(new HashSet<>());
  }

  public WebPageUrlSet(Set<String> urls) {
    this.urls = urls;
  }

  public WebPageUrlSet mergedWith(WebPageUrlSet otherUrlSet) {
    Set<String> mergedUrls = new HashSet<>(urls);
    mergedUrls.addAll(otherUrlSet.getUrls());
    return new WebPageUrlSet(mergedUrls);
  }

  public WebPageUrlSet asymmetricDiff(WebPageUrlSet otherUrlSet) {
    Set<String> diff = new HashSet<>(urls);
    diff.removeAll(otherUrlSet.getUrls());
    return new WebPageUrlSet(diff);
  }

  public boolean isEmpty() {
    return urls.isEmpty();
  }

  public String[] toArray() {
    return getUrls().toArray(String[]::new);
  }

  public Set<String> getUrls() {
    return new HashSet<>(urls);
  }

}
