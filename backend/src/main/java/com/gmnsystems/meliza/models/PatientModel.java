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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "patient")
public class PatientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPatient;

  @Column(nullable = false, length = 20)
  private String firstName;
  @Column(nullable = false, length = 40)
  private String lastName;
  @Column(nullable = false, length = 60)
  private String email;
  @Column(nullable = false, length = 20)
  private String phone;
  @Column(nullable = false, length = 14)
  private String cpf;
  @Column(nullable = false, length = 10)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
  private LocalDate dateCreated;

  // Vários pacientes pertencem a um usuário
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference para evitar loops dentro do objeto JSON
  // e consequentemente evitar exceções
  @JsonBackReference
  private UserModel user;

  public PatientModel() {
  }

  public PatientModel(Long idPatient, String firstName, String lastName, String email, String phone, String cpf,
      LocalDate dateCreated, UserModel user) {
    this.idPatient = idPatient;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.cpf = cpf;
    this.dateCreated = dateCreated;
    this.user = user;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idPatient == null) ? 0 : idPatient.hashCode());
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
    PatientModel other = (PatientModel) obj;
    if (idPatient == null) {
      if (other.idPatient != null)
        return false;
    } else if (!idPatient.equals(other.idPatient))
      return false;
    return true;
  }

}