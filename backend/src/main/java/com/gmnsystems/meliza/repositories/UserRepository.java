package com.gmnsystems.meliza.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
  // Custom query para buscar um usuário pelo username
  Optional<UserModel> findByUsername(String username);
}
