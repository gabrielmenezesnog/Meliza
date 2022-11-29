package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "history")
public class HistoryModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idHistory;

  private String message;
  private LocalDate dateCreated;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT")
  private LocalTime timeCreated;

  public HistoryModel() {
  }

  public HistoryModel(Long idHistory, String message, LocalDate dateCreated, LocalTime timeCreated) {
    this.idHistory = idHistory;
    this.message = message;
    this.dateCreated = dateCreated;
    this.timeCreated = timeCreated;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdHistory() {
    return idHistory;
  }

  public void setIdHistory(Long idHistory) {
    this.idHistory = idHistory;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalTime getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(LocalTime timeCreated) {
    this.timeCreated = timeCreated;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idHistory == null) ? 0 : idHistory.hashCode());
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
    HistoryModel other = (HistoryModel) obj;
    if (idHistory == null) {
      if (other.idHistory != null)
        return false;
    } else if (!idHistory.equals(other.idHistory))
      return false;
    return true;
  }
}
