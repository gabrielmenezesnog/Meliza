package com.gmnsystems.meliza.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.models.UserModel;

public class ScheduleDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long idSchedule;
  private String name;
  private LocalDate dateCreated;
  private UserModel user;

  public ScheduleDTO() {

  }

  public ScheduleDTO(ScheduleModel obj) {
    idSchedule = obj.getIdSchedule();
    name = obj.getName();
    dateCreated = obj.getDateCreated();
    user = obj.getUser();
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
}
