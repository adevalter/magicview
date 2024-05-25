package br.com.adeweb.magicview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adeweb.magicview.models.User;
import br.com.adeweb.magicview.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public User saveUser(User user) {
    validateAddUser(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }

  public void validateAddUser(User user) {

    if (findByEmail(user.getEmail()).isPresent()) {
      throw new RuntimeException("Email ja Cadastrado");
    }

    if (findByNick(user.getNick()).isPresent()) {
      throw new RuntimeException("Nick ja Cadastrado");
    }
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public Optional<User> findByNick(String nick) {
    return repository.findByNick(nick);
  }

  public Page<User> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

}
