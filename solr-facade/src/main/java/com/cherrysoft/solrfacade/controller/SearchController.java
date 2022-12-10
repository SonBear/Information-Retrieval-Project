package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.SearchResultDTO;
import com.cherrysoft.solrfacade.mapper.SearchResultMapper;
import com.cherrysoft.solrfacade.model.ParamSpec;
import com.cherrysoft.solrfacade.model.SearchSpec;
import com.cherrysoft.solrfacade.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SearchController {
  private final SearchService searchService;
  private final SearchResultMapper mapper;

  @RequestMapping("/search")
  public ResponseEntity<SearchResultDTO> search(
      @RequestParam String query,
      @RequestParam Map<String, String> additionalParams
  ) {
    var convertedParams = ParamSpec.convert(additionalParams);
    SearchSpec searchSpec = new SearchSpec(query, convertedParams);
    var result = searchService.search(searchSpec);
    return ResponseEntity.ok(mapper.toDto(result));
  }

}
