package br.com.adeweb.magicview.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adeweb.magicview.models.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, UUID> {

}