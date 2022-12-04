package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.WebPageDocumentDTO;
import com.cherrysoft.solrfacade.model.WebPageDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WebPageDocumentMapper {

  WebPageDocumentDTO toDto(WebPageDocument webPageDocument);

  List<WebPageDocumentDTO> toDtoList(List<WebPageDocument> webPageDocuments);

}
