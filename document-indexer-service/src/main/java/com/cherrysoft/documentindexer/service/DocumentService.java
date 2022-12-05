package com.cherrysoft.documentindexer.service;

import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
  private final HttpSolrClient solrClient;

  public void uploadFiles(MultipartFile[] files) {
    uploadFiles(List.of(files));
  }

  public void uploadFiles(List<MultipartFile> files) {
    try {
      tryUploadFiles(files);
    } catch (IOException | SolrServerException e) {
      throw new RuntimeException(e);
    }
  }

  private void tryUploadFiles(List<MultipartFile> files) throws IOException, SolrServerException {
    var req = createUpdateRequest();
    for (MultipartFile file : files) {
      var byteArray = toByteArrayStream(file);
      req.addContentStream(byteArray);
    }
    NamedList<Object> result = solrClient.request(req);
    System.out.println("Result: " + result);
  }

  private ContentStreamUpdateRequest createUpdateRequest() {
    var request = new ContentStreamUpdateRequest("/update/extract");
    request.setParam("commit", "true");
    return request;
  }

  private ContentStreamBase.ByteArrayStream toByteArrayStream(MultipartFile file) throws IOException {
    return new ContentStreamBase.ByteArrayStream(file.getBytes(), file.getName(), file.getContentType());
  }

}
