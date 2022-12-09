package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.FacetResultDTO;
import com.cherrysoft.solrfacade.model.FacetResult;
import org.mapstruct.Mapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public abstract class FacetResultMapper {

  public FacetResultDTO toFacetResultDto(FacetResult facetResult) {
    return FacetResultDTO.builder()
        .documentType(toFacetedItemDtoList(facetResult.getDocumentType()))
        .language(toFacetedItemDtoList(facetResult.getLanguage()))
        .build();
  }

  private List<FacetResultDTO.FacetedItemDTO> toFacetedItemDtoList(List<FacetResult.FacetedItem> facetedItems) {
    return facetedItems.stream()
        .map(facetedItem -> new FacetResultDTO.FacetedItemDTO(facetedItem.getClassifier(), facetedItem.getCount()))
        .collect(toList());
  }

}
