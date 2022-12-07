package com.gmnsystems.meliza.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
  private Long id;
  private String email;
  private String tokenType = "Bearer";
  private String accessToken;

  public AuthResponseDTO(Long id, String email, String accessToken) {
    this.id = id;
    this.email = email;
    this.accessToken = accessToken;
  }
}