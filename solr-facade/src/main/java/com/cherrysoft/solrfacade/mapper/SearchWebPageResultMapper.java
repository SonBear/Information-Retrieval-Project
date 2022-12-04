package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.SearchWebPageResultDTO;
import com.cherrysoft.solrfacade.model.SearchWebPageResult;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Mapper(componentModel = "spring")
public abstract class SearchWebPageResultMapper {
  private WebPageDocumentMapper webPageDocumentMapper;

  public SearchWebPageResultDTO toDto(SearchWebPageResult result) {
    var webPageDocumentDtos = webPageDocumentMapper.toDtoList(result.getWebPageDocuments());
    return new SearchWebPageResultDTO(
        webPageDocumentDtos,
        Collections.unmodifiableList(result.getHighlightSnippets())
    );
  }

  @Autowired
  public void setWebPageDocumentMapper(WebPageDocumentMapper mapper) {
    this.webPageDocumentMapper = mapper;
  }

}
