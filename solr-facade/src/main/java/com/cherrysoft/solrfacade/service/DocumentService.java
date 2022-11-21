package com.cherrysoft.solrfacade.service;

import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequestScope
@RequiredArgsConstructor
public class DocumentService {
  private final HttpSolrClient solrClient;
  private final ContentStreamUpdateRequest req;

  public void uploadTestPdf() {
    try {
      File testFile = ResourceUtils.getFile("classpath:sample.pdf");
      req.addFile(testFile, MediaType.APPLICATION_PDF_VALUE);
      NamedList<Object> result = solrClient.request(req);
      System.out.println("Result: " + result);
    } catch (IOException | SolrServerException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public void uploadPdf(MultipartFile file) {
    try {
      var pdf = new ContentStreamBase.ByteArrayStream(file.getBytes(), "pdf", MediaType.APPLICATION_PDF_VALUE);
      req.addContentStream(pdf);
      NamedList<Object> result = solrClient.request(req);
      System.out.println("Result: " + result);
    } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
