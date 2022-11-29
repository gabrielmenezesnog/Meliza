package com.gmnsystems.meliza.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.models.UserModel;

public class PlanDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long idPlan;
  private String name;
  private Integer maxConsultations;
  private Double price;
  private Integer durationInMonths;
  private LocalDate dateCreated;
  private List<SubscriptionModel> subscriptions = new ArrayList<>();
  private UserModel user;

  public PlanDTO() {
  }

  public PlanDTO(PlanModel obj) {
    idPlan = obj.getIdPlan();
    name = obj.getName();
    maxConsultations = obj.getMaxConsultations();
    price = obj.getPrice();
    durationInMonths = obj.getDurationInMonths();
    dateCreated = obj.getDateCreated();
    user = obj.getUser();
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdPlan() {
    return idPlan;
  }

  public void setIdPlan(Long idPlan) {
    this.idPlan = idPlan;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMaxConsultations() {
    return maxConsultations;
  }

  public void setMaxConsultations(Integer maxConsultations) {
    this.maxConsultations = maxConsultations;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getDurationInMonths() {
    return durationInMonths;
  }

  public void setDurationInMonths(Integer durationInMonths) {
    this.durationInMonths = durationInMonths;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public List<SubscriptionModel> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<SubscriptionModel> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

}
