package com.cherrysoft.crawler.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Getter
@ToString
@RequiredArgsConstructor
public class WebPageUrlSetDTO {
  private final Set<@URL String> urls;

  public WebPageUrlSetDTO() {
    this.urls = Set.of();
  }

}
