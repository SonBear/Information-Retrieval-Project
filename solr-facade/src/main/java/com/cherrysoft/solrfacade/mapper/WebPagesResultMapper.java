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
    var webPageDocumentDtoList = webPageDocumentMapper.toDtoList(result.getWebPageDocuments());
    return WebPagesResultDTO.builder()
        .documents(webPageDocumentDtoList)
        .hlSnippets(Collections.unmodifiableList(result.getHighlightSnippets()))
        .spellcheckResult(spellcheckResultDto(result.getSpellcheckResult()))
        .build();
  }

  private WebPagesResultDTO.SpellcheckResultDTO spellcheckResultDto(WebPagesResult.SpellcheckResult spellcheckResult) {
    return WebPagesResultDTO.SpellcheckResultDTO.builder()
        .suggestions(spellcheckResult.getSuggestions())
        .collations(spellcheckResult.getCollations())
        .build();
  }

  @Autowired
  public void setWebPageDocumentMapper(WebPageDocumentMapper mapper) {
    this.webPageDocumentMapper = mapper;
  }

}
