package com.cherrysoft.documentindexer.controller;

import com.cherrysoft.documentindexer.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentController {
  private final DocumentService documentService;

  @GetMapping("/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("Pong");
  }

  @PostMapping("/test-pdf")
  public ResponseEntity<Void> uploadTestPdf() {
    documentService.uploadTestPdf();
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/upload-pdf")
  public ResponseEntity<String> testContentType(@RequestParam("file") MultipartFile file) {
    documentService.uploadPdf(file);
    return ResponseEntity.noContent().build();
  }

}
