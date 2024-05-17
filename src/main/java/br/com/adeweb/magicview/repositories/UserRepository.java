package br.com.adeweb.magicview.repositories;

import br.com.adeweb.magicview.models.User;
import jakarta.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByNick(String nick);

}
