package br.com.adeweb.magicview.controllers;

import br.com.adeweb.magicview.models.Upload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.adeweb.magicview.services.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadController {

  private final UploadService service;

  public UploadController(UploadService service) {
    this.service = service;
  }

  @PostMapping("/single")
  public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
    Upload fileSave = service.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(fileSave.getId());
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
  
}
