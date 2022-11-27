package com.cherrysoft.crawler.controller;

import com.cherrysoft.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlerController {
  private final CrawlerService crawlerService;

  @GetMapping("/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("Pong");
  }

  @PostMapping("/index-url")
  public ResponseEntity<Void> indexUrl(@RequestParam("url") String url) {
    crawlerService.indexUrl(url);
    return ResponseEntity.noContent().build();
  }

}
