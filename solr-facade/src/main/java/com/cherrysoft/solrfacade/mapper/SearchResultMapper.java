package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.SearchResultDTO;
import com.cherrysoft.solrfacade.model.SearchResult;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Mapper(componentModel = "spring")
public abstract class SearchResultMapper {
  private RecoveredDocumentMapper recoveredDocumentMapper;

  public SearchResultDTO toDto(SearchResult result) {
    var recoveredDocumentDtoList = recoveredDocumentMapper.toDtoList(result.getRecoveredDocuments());
    return SearchResultDTO.builder()
        .documents(recoveredDocumentDtoList)
        .hlSnippets(Collections.unmodifiableList(result.getHighlightSnippets()))
        .spellcheckResult(spellcheckResultDto(result.getSpellcheckResult()))
        .build();
  }

  private SearchResultDTO.SpellcheckResultDTO spellcheckResultDto(SearchResult.SpellcheckResult result) {
    return SearchResultDTO.SpellcheckResultDTO.builder()
        .suggestions(result.getSuggestions())
        .collations(result.getCollations())
        .build();
  }

  @Autowired
  public void setRecoveredDocumentMapper(RecoveredDocumentMapper mapper) {
    this.recoveredDocumentMapper = mapper;
  }

}
