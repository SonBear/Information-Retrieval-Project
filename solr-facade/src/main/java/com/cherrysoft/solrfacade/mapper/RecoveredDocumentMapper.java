package com.cherrysoft.solrfacade.mapper;

import com.cherrysoft.solrfacade.controller.dto.RecoveredDocumentDTO;
import com.cherrysoft.solrfacade.model.RecoveredDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecoveredDocumentMapper {

  RecoveredDocumentDTO toDto(RecoveredDocument documents);

  List<RecoveredDocumentDTO> toDtoList(List<RecoveredDocument> documents);

}
