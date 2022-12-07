package com.cherrysoft.crawler.controller;

import com.cherrysoft.crawler.controller.dto.IndexedWebPagesResultDTO;
import com.cherrysoft.crawler.controller.dto.WebPageUrlSetDTO;
import com.cherrysoft.crawler.mapper.IndexedWebPagesResultMapper;
import com.cherrysoft.crawler.mapper.WebPageUrlSetMapper;
import com.cherrysoft.crawler.model.IndexedWebPagesResult;
import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.service.IndexingWebPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class IndexingWebPagesController {
  private final IndexingWebPagesService indexingWebPagesService;
  private final WebPageUrlSetMapper mapper;
  private final IndexedWebPagesResultMapper resultMapper;

  @GetMapping("/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("Pong");
  }

  @PostMapping("/index")
  public ResponseEntity<IndexedWebPagesResultDTO> indexUrls(@RequestBody @Valid WebPageUrlSetDTO urlSetDto) {
    WebPageUrlSet urlSet = mapper.toWebPageUrlSet(urlSetDto);
    IndexedWebPagesResult result = indexingWebPagesService.indexUrls(urlSet);
    return ResponseEntity.ok(resultMapper.toIndexedWebPagesResultDto(result));
  }

  @PostMapping("/reindex")
  public ResponseEntity<IndexedWebPagesResultDTO> reindex(@RequestBody @Valid WebPageUrlSetDTO urlSetDtoToReindex) {
    WebPageUrlSet urlSetToReindex = mapper.toWebPageUrlSet(urlSetDtoToReindex);
    IndexedWebPagesResult result = indexingWebPagesService.reindexUrls(urlSetToReindex);
    return ResponseEntity.ok(resultMapper.toIndexedWebPagesResultDto(result));
  }

  @DeleteMapping("/urls")
  public ResponseEntity<WebPageUrlSetDTO> deleteUrls(@RequestBody @Valid WebPageUrlSetDTO urlSetDtoToDelete) {
    WebPageUrlSet urlSetToDelete = mapper.toWebPageUrlSet(urlSetDtoToDelete);
    WebPageUrlSet result = indexingWebPagesService.deleteUrls(urlSetToDelete);
    return ResponseEntity.ok(mapper.toWebPageUrlSetDto(result));
  }

}
