package com.gmnsystems.meliza.models.enums;

// Tipo enumerado responsável por dar o status
// de uma consulta
public enum SubscriptionStatus {

  WAITING_CONFIRMATION(1),
  ACTIVE(2),
  EXPIRED(3),
  CANCELED(4);

  private int code;

  // Construtor padrão do tipo enumerado
  // ConsultationStatus
  private SubscriptionStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  // For para verificar se o código passado
  // é igual a algum dos códigos disponíveis
  public static SubscriptionStatus valueOf(int code) {
    for (SubscriptionStatus value : SubscriptionStatus.values()) {
      if (value.getCode() == code){
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid ConsultationStatus code");
  }
}