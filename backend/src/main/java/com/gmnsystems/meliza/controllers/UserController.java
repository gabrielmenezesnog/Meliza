package com.gmnsystems.meliza.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gmnsystems.meliza.dto.UserDTO;
import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.services.ConsultationService;
import com.gmnsystems.meliza.services.PatientService;
import com.gmnsystems.meliza.services.PlanService;
import com.gmnsystems.meliza.services.ScheduleService;
import com.gmnsystems.meliza.services.SubscriptionService;
import com.gmnsystems.meliza.services.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private PlanService planService;

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private PatientService patientService;

  @Autowired
  private ConsultationService consultationService;

  @GetMapping("/users")
  public List<UserDTO> getUsers() {
    List<UserModel> list = userService.getUsers();
    List<UserDTO> listDto = list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
    return listDto;
  }

  // Buscar um usuário pelo id
  @GetMapping("/users/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    return ResponseEntity.ok().body(new UserDTO(user));
  }

  // Endpoint para registrar um usuário
  @PostMapping("/signup")
  public ResponseEntity<Void> createuser(@RequestBody UserDTO userDTO) {
    UserModel user = userService.fromDTO(userDTO);
    user = userService.registerUser(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdUser()).toUri();
    return ResponseEntity.created(uri).build();
  }

  // Endpoint para deletar um usuário pelo id
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // Endpoint para atualizar um usuário
  @PutMapping("/users/{id}")
  public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
    UserModel user = userService.fromDTO(userDTO);
    user.setIdUser(id);
    user = userService.update(user);
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar as agendas de um usuário
  @GetMapping("/users/{id}/schedules")
  public ResponseEntity<List<ScheduleModel>> findSchedules(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    List<ScheduleModel> schedule = scheduleService.getSchedules();

    // Acumulador que irá receber todas as agendas do objeto
    List<ScheduleModel> accumulator = new ArrayList<>();

    schedule.forEach(item -> {
      if (item.getUser() == user) {
        accumulator.add(item);
      }
    });

    try {
      return ResponseEntity.ok().body(accumulator);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar os planos de um usuário
  @GetMapping("/users/{id}/plans")
  public ResponseEntity<List<PlanModel>> findPlans(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    List<PlanModel> plan = planService.getPlans();

    // Acumulador que irá receber todas os planos do objeto
    List<PlanModel> accumulator = new ArrayList<>();

    plan.forEach(item -> {
      if (item.getUser() == user) {
        accumulator.add(item);
      }
    });

    try {
      return ResponseEntity.ok().body(accumulator);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar as assinaturas de planos de um usuário
  @GetMapping("/users/{id}/subscriptions")
  public ResponseEntity<List<SubscriptionModel>> findSubscriptions(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    List<SubscriptionModel> subscription = subscriptionService.findAll();

    // Acumulador que irá receber todas os planos do objeto
    List<SubscriptionModel> accumulator = new ArrayList<>();

    subscription.forEach(item -> {
      if (item.getUser() == user) {
        accumulator.add(item);
      }
    });

    try {
      return ResponseEntity.ok().body(accumulator);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar os pacientes de um usuário
  @GetMapping("/users/{id}/patients")
  public ResponseEntity<List<PatientModel>> findPatients(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    List<PatientModel> patient = patientService.findAll();

    // Acumulador que irá receber todos os planos do objeto
    List<PatientModel> accumulator = new ArrayList<>();

    patient.forEach(item -> {
      if (item.getUser() == user) {
        accumulator.add(item);
      }
    });

    try {
      return ResponseEntity.ok().body(accumulator);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar as consultas de um usuário
  @GetMapping("/users/{id}/consultations")
  public ResponseEntity<List<ConsultationModel>> findConsultations(@PathVariable Long id) {
    UserModel user = userService.findById(id);
    List<ConsultationModel> consultation = consultationService.getConsultations();

    // Acumulador que irá receber todas as consultas do objeto
    List<ConsultationModel> accumulator = new ArrayList<>();

    consultation.forEach(item -> {
      if (item.getUser() == user) {
        accumulator.add(item);
      }
    });

    try {
      return ResponseEntity.ok().body(accumulator);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return ResponseEntity.noContent().build();
  }
}
