package com.gmnsystems.meliza.controllers;

import java.net.URI;
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

import com.gmnsystems.meliza.dto.ConsultationDTO;
import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.services.ConsultationService;

@RestController
@RequestMapping(value = "/consultations")
public class ConsultationController {

  @Autowired
  private ConsultationService consultationService;

  // Listar todas as consultas
  @GetMapping
  public ResponseEntity<List<ConsultationModel>> getConsultations() {
    return ResponseEntity.ok().body(consultationService.getConsultations());
  }

  // Buscar uma consulta pelo id
  @GetMapping("/{id}")
  public ResponseEntity<ConsultationModel> findById(@PathVariable Long id) {
    ConsultationModel consultation = consultationService.findById(id);
    return ResponseEntity.ok().body(consultation);
  }

  // Criar uma consulta
  @PostMapping
  public ResponseEntity<ConsultationModel> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
    ConsultationModel consultation = consultationService.fromDTO(consultationDTO);
    consultation = consultationService.createConsultation(consultation);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(consultation.getIdConsultation()).toUri();
    return ResponseEntity.created(uri).build();
  }

  // Atualizar uma consulta
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody ConsultationDTO consultationDTO, @PathVariable Long id) {
    ConsultationModel consultation = consultationService.fromDTO(consultationDTO);
    consultation.setIdConsultation(id);
    consultation = consultationService.update(consultation);
    return ResponseEntity.noContent().build();
  }

  // Deletar uma consulta
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    consultationService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
