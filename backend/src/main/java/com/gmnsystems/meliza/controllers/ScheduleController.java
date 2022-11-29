package com.gmnsystems.meliza.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gmnsystems.meliza.dto.ScheduleDTO;
import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.services.ConsultationService;
import com.gmnsystems.meliza.services.ScheduleService;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private ConsultationService consultationService;

  // Listar agendas
  @GetMapping
  public List<ScheduleModel> getSchedules() {
    return scheduleService.getSchedules();
  }

  // Buscar uma agenda pelo id
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleModel> findById(@PathVariable Long id) {
    ScheduleModel schedule = scheduleService.findById(id);
    return ResponseEntity.ok().body(schedule);
  }

  // Criar uma agenda
  @PostMapping
  public ResponseEntity<Void> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
    ScheduleModel schedule = scheduleService.fromDTO(scheduleDTO);
    schedule = scheduleService.createSchedule(schedule);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(schedule.getIdSchedule())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  // Atualizar uma agenda
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody ScheduleDTO scheduleDTO, @PathVariable Long id) {
    ScheduleModel schedule = scheduleService.fromDTO(scheduleDTO);
    schedule.setIdSchedule(id);
    schedule = scheduleService.update(schedule);
    return ResponseEntity.noContent().build();
  }

  // Deletar uma agenda
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    scheduleService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar as consultas de uma agenda
  @GetMapping("/{id}/consultations")
  public ResponseEntity<List<ConsultationModel>> findConsultations(@PathVariable Long id) {
    ScheduleModel schedule = scheduleService.findById(id);
    List<ConsultationModel> consultation = consultationService.getConsultations();

    // Acumulador que irá receber todas os planos do objeto
    List<ConsultationModel> accumulator = new ArrayList<>();

    consultation.forEach(item -> {
      if (item.getSchedule() == schedule) {
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
