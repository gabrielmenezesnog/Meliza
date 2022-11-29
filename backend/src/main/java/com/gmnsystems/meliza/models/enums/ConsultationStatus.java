package com.gmnsystems.meliza.models.enums;

// Tipo enumerado responsável por dar o status
// de uma consulta
public enum ConsultationStatus {

  WAITING_CONFIRMATION(1),
  CONFIRMED(2),
  MISSED(3),
  CANCELED(4);

  private int code;

  // Construtor padrão do tipo enumerado
  // ConsultationStatus
  private ConsultationStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  // For para verificar se o código passado
  // é igual a algum dos códigos disponíveis
  public static ConsultationStatus valueOf(int code) {
    for (ConsultationStatus value : ConsultationStatus.values()) {
      if (value.getCode() == code){
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid ConsultationStatus code");
  }
}