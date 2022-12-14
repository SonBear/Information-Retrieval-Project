package com.cherrysoft.crawler.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class URLUtils {

  public static String encodeUrl(String url) {
    return UriComponentsBuilder.fromHttpUrl(url)
        .build()
        .encode()
        .toUriString();
  }

  public static String decodeUrl(String url) {
    return URLDecoder.decode(url, StandardCharsets.UTF_8);
  }

}
