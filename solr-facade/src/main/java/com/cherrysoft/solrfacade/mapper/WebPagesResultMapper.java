package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.WebPagesResultDTO;
import com.cherrysoft.solrfacade.model.WebPagesResult;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Mapper(componentModel = "spring")
public abstract class WebPagesResultMapper {
  private WebPageDocumentMapper webPageDocumentMapper;

  public WebPagesResultDTO toDto(WebPagesResult result) {
    var webPageDocumentDtos = webPageDocumentMapper.toDtoList(result.getWebPageDocuments());
    return new WebPagesResultDTO(
        webPageDocumentDtos,
        Collections.unmodifiableList(result.getHighlightSnippets())
    );
  }

  @Autowired
  public void setWebPageDocumentMapper(WebPageDocumentMapper mapper) {
    this.webPageDocumentMapper = mapper;
  }

}
