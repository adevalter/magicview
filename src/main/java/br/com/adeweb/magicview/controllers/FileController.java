package br.com.adeweb.magicview.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import br.com.adeweb.magicview.dto.AttachmentDTO;
import br.com.adeweb.magicview.services.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/file")
public class FileController {

  private final UploadService service;

  public FileController(UploadService service) {
    this.service = service;
  }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]>  uploadFile(@PathVariable("id") UUID id) throws Exception {

      AttachmentDTO attachmentDTO = service.getFileUrl(id);

      return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(attachmentDTO.getFileType()))
      .body(attachmentDTO.getFile());
    }
}
