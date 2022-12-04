package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.SearchWebPageResultDTO;
import com.cherrysoft.solrfacade.mapper.SearchWebPageResultMapper;
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
  private final SearchWebPageResultMapper mapper;

  @RequestMapping("/search")
  public ResponseEntity<SearchWebPageResultDTO> search(@RequestParam String query) {
    var result = searchWebPageService.search(query);
    return ResponseEntity.ok(mapper.toDto(result));
  }

}
