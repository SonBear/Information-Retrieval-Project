package com.cherrysoft.crawler.mapper;

import com.cherrysoft.crawler.controller.dto.IndexedWebPagesResultDTO;
import com.cherrysoft.crawler.model.IndexedWebPagesResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WebPageUrlSetMapper.class)
public interface IndexedWebPagesResultMapper {

  @Mapping(target = "payload", source = "urlSetPayload")
  IndexedWebPagesResultDTO toIndexedWebPagesResultDto(IndexedWebPagesResult result);

}
