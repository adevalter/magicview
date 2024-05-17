package br.com.adeweb.magicview.services;

import java.util.UUID;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.adeweb.magicview.dto.AttachmentDTO;
import br.com.adeweb.magicview.models.Upload;
import br.com.adeweb.magicview.models.User;
import br.com.adeweb.magicview.repositories.UploadRepository;
import br.com.adeweb.magicview.security.UserDetailsService;

@Service
public class UploadService {

  private final Path root = Paths.get("uploads");
  private final UserDetailsService userDetailsService;
  private final UploadRepository repository;

  public UploadService(UserDetailsService userDetailsService, UploadRepository repository) {
    this.userDetailsService = userDetailsService;
    this.repository = repository;
  }

  public void init() {
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Pasta de upload não existe");
    }
  }

  public void save(MultipartFile file) {
    try {
      Date date = new Date();
      Long getTime = date.getTime();
      String idUser = userDetailsService.getUserAuthentication();
      String fileType = file.getContentType();
      String extension = FilenameUtils.getExtension(file.getOriginalFilename());    
      
      String nameFile = idUser + "_" +getTime + "." + extension;
      Files.copy(file.getInputStream(), this.root.resolve(nameFile));

      User user = User.builder().id(UUID.fromString(idUser)).build();
      Upload upload = Upload.builder()
        .fileName(nameFile)
        .fileType(fileType)
        .user(user)
        .build();

      repository.save(upload);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage()); 
    }
  }

  public Upload getById(UUID id){
    return repository.findById(id).orElseThrow();
  }

  public AttachmentDTO getFileUrl(UUID id) throws IOException{

    Upload upload = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

    InputStream in = Files.newInputStream(Paths.get("uploads/" + upload.getFileName()));

    final AttachmentDTO attachmentDTO = AttachmentDTO .builder()
                      .fileName(upload.getFileName())
                      .fileType(upload.getFileType())
                      .urlFile("uploads/" + upload.getFileName())
                      .file(IOUtils.toByteArray(in))
                      .build();

    return attachmentDTO;
  }
}
