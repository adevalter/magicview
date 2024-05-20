package br.com.adeweb.magicview.controllers;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.adeweb.magicview.dto.LoginRequest;
import br.com.adeweb.magicview.dto.LoginResponse;
import br.com.adeweb.magicview.models.User;
import br.com.adeweb.magicview.security.JWTUtil;
import br.com.adeweb.magicview.services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

  private final UserService service;
  private final AuthenticationManager authManager;
  private final JWTUtil jwtUtil;

  public UserController(UserService service, AuthenticationManager authManager, JWTUtil jwtUtil) {
    this.service = service;
    this.authManager = authManager;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(service.saveUser(user));
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {

    try {

      Optional<User> opUser = service.findByEmail(loginRequest.email());
      System.out.println(loginRequest.email());
      if (opUser.isEmpty()) {
        throw new RuntimeException("Usuário não Cadastrado");
      }
      UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(opUser.get().getId(),
          loginRequest.password());

      authManager.authenticate(authInputToken);

      String token = jwtUtil.generateToken(opUser.get());

      LoginResponse loginResponse = new LoginResponse(opUser.get().getEmail(), token);

      return loginResponse;

    } catch (AuthenticationException authExc) {

      throw new RuntimeException("Credenciais Inválidas");
    }
  }

  @GetMapping
  public ResponseEntity<?> getAllUsers (
          @RequestParam(defaultValue = "0") final Integer pageNumber,
          @RequestParam(defaultValue = "5") final Integer size
  ) {
    return ResponseEntity.ok(service.findAll(PageRequest.of(pageNumber,size)));
  }

}
