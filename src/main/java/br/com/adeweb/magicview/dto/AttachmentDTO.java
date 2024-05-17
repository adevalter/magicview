package br.com.adeweb.magicview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentDTO {
  private String fileName;
  private String fileType;
  private String urlFile;
  private byte[] file;
}
