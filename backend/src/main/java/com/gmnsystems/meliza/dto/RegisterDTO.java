package com.gmnsystems.meliza.dto;

import lombok.Data;

@Data
public class RegisterDTO {
  private String name;
  private String email;
  private String password;

  public String getUsername() {
    return this.email;
  }
}
