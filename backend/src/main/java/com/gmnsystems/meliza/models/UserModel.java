package com.gmnsystems.meliza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUser;
  @Column(nullable = false, length = 60)
  private String name;
  @Column(nullable = false, length = 60, unique = true)
  private String email;
  @Column(nullable = false, length = 60, unique = true)
  private String username;
  @Column(nullable = false, length = 60)
  private String password;

  // Vários usuários possuem várias roles
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
  private List<RoleModel> roles = new ArrayList<>();

  // Um usuário possui várias agendas
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<ScheduleModel> schedules = new ArrayList<>();

  // Um usuário possui vários planos
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<PlanModel> plans = new ArrayList<>();

  // Um usuário possui várias assinaturas para um plano
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<SubscriptionModel> subscriptions = new ArrayList<>();

  // Um usuário possui vários pacientes
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<PatientModel> patients = new ArrayList<>();

  // Um usuário possui várias consultas
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<ConsultationModel> consultations = new ArrayList<>();

  public UserModel(Long idUser, String name, String email, String password) {
    this.idUser = idUser;
    this.name = name;
    this.email = email;
    // username e email deverão ser iguais
    // devido ao processo de autenticação
    this.username = email;
    this.password = password;
  }

  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
    UserModel other = (UserModel) obj;
    if (idUser == null) {
      if (other.idUser != null)
        return false;
    } else if (!idUser.equals(other.idUser))
      return false;
    return true;
  }
}
