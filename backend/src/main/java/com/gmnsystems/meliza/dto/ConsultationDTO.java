package com.gmnsystems.meliza.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.models.enums.ConsultationStatus;

public class ConsultationDTO {

  private Long idConsultation;
  private LocalDate consultationDate;
  private LocalTime consultationTime;
  private LocalDate dateCreated;
  private ConsultationStatus consultationStatus;
  private PatientModel patient;
  private ScheduleModel schedule;
  private UserModel user;

  public ConsultationDTO() {
  }

  public ConsultationDTO(ConsultationModel obj) {
    idConsultation = obj.getIdConsultation();
    consultationDate = obj.getConsultationDate();
    consultationTime = obj.getConsultationTime();
    dateCreated = obj.getDateCreated();
    consultationStatus = obj.getConsultationStatus();
    patient = obj.getPatient();
    schedule = obj.getSchedule();
    user = obj.getUser();
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

  public void setConsultationDate(LocalDate consultationDate) {
    this.consultationDate = consultationDate;
  }

  public LocalTime getConsultationTime() {
    return consultationTime;
  }

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
    return consultationStatus;
  }

  public void setConsultationStatus(ConsultationStatus consultationStatus) {
    this.consultationStatus = consultationStatus;
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
}
