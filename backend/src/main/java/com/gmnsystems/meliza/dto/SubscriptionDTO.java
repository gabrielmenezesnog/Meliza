package com.gmnsystems.meliza.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.models.enums.SubscriptionStatus;

public class SubscriptionDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long idSubscription;
  private LocalDate startDate;
  private LocalDate expirationDate;
  private SubscriptionStatus subscriptionStatus;
  private PatientModel patient;
  private PlanModel plan;
  private UserModel user;

  public SubscriptionDTO() {
  }

  public SubscriptionDTO(SubscriptionModel obj) {
    idSubscription = obj.getIdSubscription();
    startDate = obj.getStartDate();
    expirationDate = obj.getExpirationDate();
    subscriptionStatus = obj.getSubscriptionStatus();
    patient = obj.getPatient();
    plan = obj.getPlan();
    user = obj.getUser();
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
    return subscriptionStatus;
  }

  public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
    this.subscriptionStatus = subscriptionStatus;
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

}
