package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.WebPagesResultDTO;
import com.cherrysoft.solrfacade.mapper.WebPagesResultMapper;
import com.cherrysoft.solrfacade.model.SearchSpec;
import com.cherrysoft.solrfacade.service.SearchWebPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchWebPageController {
  private final SearchWebPageService searchWebPageService;
  private final WebPagesResultMapper mapper;

  @RequestMapping("/search")
  public ResponseEntity<WebPagesResultDTO> search(
      @RequestParam String query,
      @RequestParam(required = false) String dictionary
  ) {
    SearchSpec searchSpec = new SearchSpec(query, dictionary);
    var result = searchWebPageService.search(searchSpec);
    return ResponseEntity.ok(mapper.toDto(result));
  }

}
