package br.com.adeweb.magicview.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB002_PICTURES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Upload {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "FILENAME")
  private String fileName;

  @Column(name = "FILETYPE")
  private String fileType;

  @ManyToOne
  @JoinColumn(name = "USERID")
  private User user;

}
