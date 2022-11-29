package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmnsystems.meliza.models.enums.ConsultationStatus;

@Entity
@Table(name = "consultation")
public class ConsultationModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idConsultation;

  @Column(nullable = false, length = 10)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
  private LocalDate consultationDate; // data da consulta
  @Column(nullable = false, length = 8)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT")
  private LocalTime consultationTime; // horário da consulta
  @Column(nullable = false, length = 10)
  private LocalDate dateCreated; // data da criação a consulta
  @Column(nullable = false, length = 5)
  private Integer consultationStatus;

  // Uma consulta possui um único paciente
  @OneToOne
  private PatientModel patient;

  // Várias consultas pertencem a uma agenda
  @ManyToOne
  @JoinColumn(name = "schedule_id", nullable = false)
  private ScheduleModel schedule;

  // Várias consultas pertencem a um usuário
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  @JsonBackReference
  private UserModel user;

  public ConsultationModel() {
  }

  public ConsultationModel(Long idConsultation, LocalDate consultationDate, LocalTime consultationTime,
      LocalDate dateCreated, ConsultationStatus consultationStatus, PatientModel patient, ScheduleModel schedule, UserModel user) {
    this.idConsultation = idConsultation;
    this.consultationDate = consultationDate;
    this.consultationTime = consultationTime;
    this.dateCreated = dateCreated;
    setConsultationStatus(consultationStatus);
    this.patient = patient;
    this.schedule = schedule;
    this.user = user;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdConsultation() {
    return idConsultation;
  }

  public void setIdConsultation(Long idConsultation) {
    this.idConsultation = idConsultation;
  }

  public LocalDate getConsultationDate() {
    return consultationDate;
  }

  public void setConsultationDate(String consultationDate) {
    this.consultationDate = LocalDate.parse(consultationDate);
  }

  public LocalTime getConsultationTime() {
    return consultationTime;
  }

  // Recebendo data em string e passando para LocalTime
  public void setConsultationTime(LocalTime consultationTime) {
    this.consultationTime = consultationTime;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public ConsultationStatus getConsultationStatus() {
    return ConsultationStatus.valueOf(consultationStatus);
  }

  public void setConsultationStatus(ConsultationStatus consultationStatus) {
    if (consultationStatus != null) {
      this.consultationStatus = consultationStatus.getCode();
    }
  }

  public PatientModel getPatient() {
    return patient;
  }

  public void setPatient(PatientModel patient) {
    this.patient = patient;
  }

  public ScheduleModel getSchedule() {
    return schedule;
  }

  public void setSchedule(ScheduleModel schedule) {
    this.schedule = schedule;
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
    result = prime * result + ((idConsultation == null) ? 0 : idConsultation.hashCode());
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
    ConsultationModel other = (ConsultationModel) obj;
    if (idConsultation == null) {
      if (other.idConsultation != null)
        return false;
    } else if (!idConsultation.equals(other.idConsultation))
      return false;
    return true;
  }

}
