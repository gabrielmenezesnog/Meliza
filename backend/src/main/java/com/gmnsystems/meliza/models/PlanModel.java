package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plan")
public class PlanModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPlan;

  @Column(nullable = false, length = 60)
  private String name;
  @Column(nullable = false, length = 3)
  private Integer maxConsultations;
  @Column(nullable = false, length = 10)
  private Double price;
  @Column(nullable = false, length = 2)
  private Integer durationInMonths;
  @Column(nullable = false, length = 10)
  private LocalDate dateCreated;

  // Um plano possui várias assinaturas
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<SubscriptionModel> subscriptions = new ArrayList<>();

  // Vários planos pertencem a um único usuário
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  @JsonBackReference
  private UserModel user;;

  public PlanModel() {
  }

  public PlanModel(Long idPlan, String name, Integer maxConsultations, Double price, Integer durationInMonths,
      LocalDate dateCreated, UserModel user) {
    this.idPlan = idPlan;
    this.name = name;
    this.maxConsultations = maxConsultations;
    this.price = price;
    this.durationInMonths = durationInMonths;
    this.dateCreated = dateCreated;
    this.user = user;
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PlanModel other = (PlanModel) obj;
    if (idPlan == null) {
      if (other.idPlan != null)
        return false;
    } else if (!idPlan.equals(other.idPlan))
      return false;
    return true;
  }

}