package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.SearchResultDTO;
import com.cherrysoft.solrfacade.mapper.SearchResultMapper;
import com.cherrysoft.solrfacade.model.SearchSpec;
import com.cherrysoft.solrfacade.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
  private final SearchService searchService;
  private final SearchResultMapper mapper;

  @RequestMapping("/search")
  public ResponseEntity<SearchResultDTO> search(
      @RequestParam String query,
      @RequestParam(required = false) String dictionary
  ) {
    SearchSpec searchSpec = new SearchSpec(query, dictionary);
    var result = searchService.search(searchSpec);
    return ResponseEntity.ok(mapper.toDto(result));
  }

}
