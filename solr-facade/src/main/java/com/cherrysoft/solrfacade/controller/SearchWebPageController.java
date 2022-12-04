package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.WebPageDocumentDTO;
import com.cherrysoft.solrfacade.mapper.WebPageDocumentMapper;
import com.cherrysoft.solrfacade.service.SearchWebPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchWebPageController {
  private final SearchWebPageService searchWebPageService;
  private final WebPageDocumentMapper mapper;

  @RequestMapping("/search")
  public ResponseEntity<List<WebPageDocumentDTO>> search(@RequestParam String query) {
    var result = searchWebPageService.search(query);
    return ResponseEntity.ok(mapper.toDtoList(result));
  }

}
