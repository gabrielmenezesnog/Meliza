package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "schedule")
public class ScheduleModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSchedule;

  @Column(nullable = false, length = 60)
  private String name;
  @Column(nullable = true, length = 10)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
  private LocalDate dateCreated;

  // Uma agenda possui uma lista de consultas
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "schedule")
  @JsonIgnore
  private List<ConsultationModel> consultations = new ArrayList<>();

  // Várias agendas pertencem a um único usuário
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  @JsonBackReference
  private UserModel user;

  public ScheduleModel() {
  }

  public ScheduleModel(Long idSchedule, String name, LocalDate dateCreated, UserModel user) {
    this.idSchedule = idSchedule;
    this.name = name;
    this.dateCreated = dateCreated;
    this.user = user;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdSchedule() {
    return idSchedule;
  }

  public void setIdSchedule(Long idSchedule) {
    this.idSchedule = idSchedule;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public UserModel getUser() {
    return user;
  }

  public List<ConsultationModel> getConsultations() {
    return consultations;
  }

  public void setConsultations(List<ConsultationModel> consultations) {
    this.consultations = consultations;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idSchedule == null) ? 0 : idSchedule.hashCode());
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
    ScheduleModel other = (ScheduleModel) obj;
    if (idSchedule == null) {
      if (other.idSchedule != null)
        return false;
    } else if (!idSchedule.equals(other.idSchedule))
      return false;
    return true;
  }
}
