package com.gmnsystems.meliza.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  private static final Long serialVersionUID = 1L;

  // Tratamento de exceção para id's não existentes
  // 404 - not found
  public ResourceNotFoundException(Object id) {
    super("Resource not found. Id: " + id);
  }

  public static Long getSerialversionuid() {
    return serialVersionUID;
  }

}
