package br.com.adeweb.magicview.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import br.com.adeweb.magicview.dto.AttachmentDTO;
import br.com.adeweb.magicview.services.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/file")
public class FileController {

    private final UploadService service;

    public FileController(UploadService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> uploadFile(@PathVariable("id") UUID id) throws Exception {

        AttachmentDTO attachmentDTO = service.getFileUrl(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentDTO.getFileType()))
                .body(attachmentDTO.getFile());
    }

    @GetMapping("/user")
    public ResponseEntity<List<AttachmentDTO>> getAllByUser(HttpServletRequest request) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String uri = request.getRequestURI();
        return ResponseEntity.ok().body(service.getAllByUser(url+"/file"));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<AttachmentDTO>> getAllAll(HttpServletRequest request, @PathVariable UUID id) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String uri = request.getRequestURI();
        return ResponseEntity.ok().body(service.getAll(url+"/file", id));
    }
}
