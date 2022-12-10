package com.cherrysoft.solrfacade.controller;

import com.cherrysoft.solrfacade.controller.dto.FacetResultDTO;
import com.cherrysoft.solrfacade.mapper.FacetResultMapper;
import com.cherrysoft.solrfacade.model.FacetResult;
import com.cherrysoft.solrfacade.model.FacetSpec;
import com.cherrysoft.solrfacade.model.ParamSpec;
import com.cherrysoft.solrfacade.service.FacetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FacetController {
  private final FacetService facetService;
  private final FacetResultMapper resultMapper;

  @GetMapping("/facet")
  public ResponseEntity<FacetResultDTO> getFacets(@RequestParam Map<String, String> params) {
    var convertedParams = ParamSpec.convert(params);
    FacetSpec facetSpec = new FacetSpec(convertedParams);
    FacetResult result = facetService.getFacets(facetSpec);
    return ResponseEntity.ok(resultMapper.toFacetResultDto(result));
  }

}
