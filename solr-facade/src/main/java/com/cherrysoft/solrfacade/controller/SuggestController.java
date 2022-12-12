package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.SuggestResultDTO;
import com.cherrysoft.solrfacade.mapper.SuggestResultMapper;
import com.cherrysoft.solrfacade.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuggestController {
  private final SuggestService suggestService;
  private final SuggestResultMapper resultMapper;

  @GetMapping("/suggest")
  public ResponseEntity<SuggestResultDTO> suggest(@RequestParam String query) {
    var result = suggestService.getSuggestions(query);
    return ResponseEntity.ok(resultMapper.toSuggestResultDto(result));
  }

}
