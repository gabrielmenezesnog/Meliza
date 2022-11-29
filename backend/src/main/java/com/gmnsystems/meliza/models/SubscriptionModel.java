package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gmnsystems.meliza.models.enums.SubscriptionStatus;

@Entity
@Table(name = "subscription")
public class SubscriptionModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSubscription;

  @Column(nullable = false, length = 10)
  private LocalDate startDate;
  @Column(nullable = false, length = 10)
  private LocalDate expirationDate;
  @Column(nullable = false, length = 5)
  private Integer subscriptionStatus;

  // Uma assinatura é relativa a um único paciente
  @OneToOne
  private PatientModel patient;

  // várias assinaturas são relativas a um único plano
  @ManyToOne
  @JoinColumn(name = "plan_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  private PlanModel plan;

  // Várias assinaturas pertencem a um único usuário
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  @JsonBackReference
  private UserModel user;

  public SubscriptionModel() {
  }

  public SubscriptionModel(Long idSubscription, LocalDate startDate, LocalDate expirationDate,
      SubscriptionStatus subscriptionStatus,
      PatientModel patient, PlanModel plan, UserModel user) {
    this.idSubscription = idSubscription;
    this.startDate = startDate;
    this.expirationDate = expirationDate;
    setSubscriptionStatus(subscriptionStatus);
    this.patient = patient;
    this.plan = plan;
    this.user = user;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdSubscription() {
    return idSubscription;
  }

  public void setIdSubscription(Long idSubscription) {
    this.idSubscription = idSubscription;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public SubscriptionStatus getSubscriptionStatus() {
    return SubscriptionStatus.valueOf(subscriptionStatus);
  }

  public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
    if (subscriptionStatus != null) {
      this.subscriptionStatus = subscriptionStatus.getCode();
    }
  }

  public PatientModel getPatient() {
    return patient;
  }

  public void setPatient(PatientModel patient) {
    this.patient = patient;
  }

  public PlanModel getPlan() {
    return plan;
  }

  public void setPlan(PlanModel plan) {
    this.plan = plan;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idSubscription == null) ? 0 : idSubscription.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SubscriptionModel other = (SubscriptionModel) obj;
    if (idSubscription == null) {
      if (other.idSubscription != null)
        return false;
    } else if (!idSubscription.equals(other.idSubscription))
      return false;
    return true;
  }

}
