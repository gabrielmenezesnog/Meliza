package com.gmnsystems.meliza.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.models.UserModel;

public class PatientDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long idPatient;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String cpf;
  private LocalDate dateCreated;
  private UserModel user;

  public PatientDTO() {
  }

  public PatientDTO(PatientModel obj) {
    idPatient = obj.getIdPatient();
    firstName = obj.getFirstName();
    lastName = obj.getLastName();
    email = obj.getEmail();
    phone = obj.getPhone();
    cpf = obj.getCpf();
    dateCreated = obj.getDateCreated();
    user = obj.getUser();
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getIdPatient() {
    return idPatient;
  }

  public void setIdPatient(Long idPatient) {
    this.idPatient = idPatient;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
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

  public void setUser(UserModel user) {
    this.user = user;
  }

}
