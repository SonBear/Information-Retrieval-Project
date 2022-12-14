package com.cherrysoft.crawler.model;

import com.cherrysoft.crawler.utils.URLUtils;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

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

  public WebPageUrlSet merge(WebPageUrlSet otherUrlSet) {
    Set<String> mergedUrls = new HashSet<>(getUrls());
    mergedUrls.addAll(otherUrlSet.getUrls());
    return new WebPageUrlSet(mergedUrls);
  }

  public WebPageUrlSet asymmetricDiff(WebPageUrlSet otherUrlSet) {
    Set<String> diff = new HashSet<>(getUrls());
    diff.removeAll(otherUrlSet.getUrls());
    return new WebPageUrlSet(diff);
  }

  public WebPageUrlSet intersection(WebPageUrlSet otherUrlSet) {
    Set<String> intersection = new HashSet<>(getUrls());
    intersection.retainAll(otherUrlSet.getUrls());
    return new WebPageUrlSet(intersection);
  }

  public boolean isEmpty() {
    return urls.isEmpty();
  }

  public Set<String> getUrls() {
    return new HashSet<>(urls);
  }

  public String[] toEncodedAndTrimmedUrlArray() {
    return getEncodedAndTrimmedUrls().toArray(String[]::new);
  }

  private Set<String> getEncodedAndTrimmedUrls() {
    return urls.stream()
        .map(String::trim)
        .map(URLUtils::encodeUrl)
        .collect(toSet());
  }

}
