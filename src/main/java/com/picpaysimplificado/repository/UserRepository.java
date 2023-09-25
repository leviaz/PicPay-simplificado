package com.picpaysimplificado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpaysimplificado.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByDocument(String document);

  Optional<User> findUserById(Long id);
}
