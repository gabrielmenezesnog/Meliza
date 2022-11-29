package com.gmnsystems.meliza.dto;

import lombok.Data;

@Data
public class LoginDTO {
  private String name;
  private String email;
  private String password;

  public String getUsername() {
    return this.email;
  }
}