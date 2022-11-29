package com.gmnsystems.meliza.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "role")
public class RoleModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idRole;
  @Column(nullable = false)
  private String name;
}