package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.SuggestResultDTO;
import com.cherrysoft.solrfacade.model.SuggestResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuggestResultMapper {

  SuggestResultDTO toSuggestResultDto(SuggestResult suggestResult);

}
