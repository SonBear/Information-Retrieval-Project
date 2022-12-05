package com.cherrysoft.crawler.mapper;

import com.cherrysoft.crawler.controller.dto.WebPageUrlSetDTO;
import com.cherrysoft.crawler.model.WebPageUrlSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WebPageUrlSetMapper {

  public WebPageUrlSet toWebPageUrlSet(WebPageUrlSetDTO webPageUrlSetDto) {
    return new WebPageUrlSet(webPageUrlSetDto.getUrls());
  }

  public WebPageUrlSetDTO toWebPageUrlSetDto(WebPageUrlSet webPageUrlSet) {
    return new WebPageUrlSetDTO(webPageUrlSet.getUrls());
  }

}
