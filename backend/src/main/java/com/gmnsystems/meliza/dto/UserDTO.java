package com.gmnsystems.meliza.dto;

import java.io.Serializable;


import com.gmnsystems.meliza.models.UserModel;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long idUser;
  private String name;
  private String email;
  private String password;

  public UserDTO() {
  }

  public UserDTO(UserModel obj) {
    idUser = obj.getIdUser();
    name = obj.getName();
    email = obj.getEmail();
    password = obj.getPassword();
  }
}