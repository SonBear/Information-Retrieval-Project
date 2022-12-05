package com.cherrysoft.crawler.controller;

import com.cherrysoft.crawler.controller.dto.IndexedWebPagesResultDTO;
import com.cherrysoft.crawler.controller.dto.WebPageUrlSetDTO;
import com.cherrysoft.crawler.mapper.IndexedWebPagesResultMapper;
import com.cherrysoft.crawler.mapper.WebPageUrlSetMapper;
import com.cherrysoft.crawler.model.IndexedWebPagesResult;
import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CrawlerController {
  private final CrawlerService crawlerService;
  private final WebPageUrlSetMapper mapper;
  private final IndexedWebPagesResultMapper resultMapper;

  @GetMapping("/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("Pong");
  }

  @PostMapping("/index")
  public ResponseEntity<IndexedWebPagesResultDTO> indexUrls(@RequestBody @Valid WebPageUrlSetDTO webPageUrlSetDto) {
    WebPageUrlSet urlSet = mapper.toWebPageUrlSet(webPageUrlSetDto);
    IndexedWebPagesResult result = crawlerService.indexUrls(urlSet);
    return ResponseEntity.ok(resultMapper.toIndexedWebPagesResultDto(result));
  }

}
