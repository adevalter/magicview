package br.com.adeweb.magicview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adeweb.magicview.services.UploadService;
import jakarta.annotation.Resource;

@SpringBootApplication
public class MagicviewApplication implements CommandLineRunner{

	@Resource
	UploadService service;

	public static void main(String[] args) {
		SpringApplication.run(MagicviewApplication.class, args);
	}

	@Override
  public void run(String... arg) throws Exception {
    service.init();
  }

}
