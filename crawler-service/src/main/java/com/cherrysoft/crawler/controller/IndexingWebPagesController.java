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
  private final WebPageUrlSetMapper urlSetMapper;
  private final IndexedWebPagesResultMapper resultMapper;

  @PostMapping("/index")
  public ResponseEntity<IndexedWebPagesResultDTO> indexUrls(@RequestBody @Valid WebPageUrlSetDTO urlSetDto) {
    WebPageUrlSet urlSet = urlSetMapper.toWebPageUrlSet(urlSetDto);
    IndexedWebPagesResult result = indexingWebPagesService.indexUrls(urlSet);
    return ResponseEntity.ok(resultMapper.toIndexedWebPagesResultDto(result));
  }

  @PostMapping("/reindex")
  public ResponseEntity<IndexedWebPagesResultDTO> reindex(@RequestBody @Valid WebPageUrlSetDTO urlSetDtoToReindex) {
    WebPageUrlSet urlSetToReindex = urlSetMapper.toWebPageUrlSet(urlSetDtoToReindex);
    IndexedWebPagesResult result = indexingWebPagesService.reindexUrls(urlSetToReindex);
    return ResponseEntity.ok(resultMapper.toIndexedWebPagesResultDto(result));
  }

  @GetMapping("/urls")
  public ResponseEntity<WebPageUrlSetDTO> urls() {
    WebPageUrlSet urlSet = indexingWebPagesService.getIndexedUrls();
    return ResponseEntity.ok(urlSetMapper.toWebPageUrlSetDto(urlSet));
  }

  @DeleteMapping("/urls")
  public ResponseEntity<WebPageUrlSetDTO> deleteUrls(@RequestBody @Valid WebPageUrlSetDTO urlSetDtoToDelete) {
    WebPageUrlSet urlSetToDelete = urlSetMapper.toWebPageUrlSet(urlSetDtoToDelete);
    WebPageUrlSet result = indexingWebPagesService.deleteUrls(urlSetToDelete);
    return ResponseEntity.ok(urlSetMapper.toWebPageUrlSetDto(result));
  }

}
