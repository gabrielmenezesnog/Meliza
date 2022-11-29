package com.gmnsystems.meliza.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
  // Custom query para buscar uma role pelo seu nome
  // atenção: busca pelo <nome> e não <username>, pois é uma role
  Optional<RoleModel> findByName(String name);
}
