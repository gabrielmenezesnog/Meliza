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

import com.gmnsystems.meliza.dto.PatientDTO;
import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.services.PatientService;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

  @Autowired
  private PatientService patientService;

  // Buscar todos pacientes
  @GetMapping
  public ResponseEntity<List<PatientModel>> findAll() {
    List<PatientModel> patientsList = patientService.findAll();
    return ResponseEntity.ok().body(patientsList);
  }

  // Buscar paciente pelo id
  @GetMapping("/{id}")
  public ResponseEntity<PatientModel> findById(@PathVariable Long id) {
    PatientModel patient = patientService.findById(id);
    return ResponseEntity.ok().body(patient);
  }

  // Registrar paciente
  @PostMapping
  public ResponseEntity<Void> createPatient(@RequestBody PatientModel patient) {
    patient = patientService.registerPatient(patient);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getIdPatient())
        .toUri();
    return ResponseEntity.created(uri).build();

  }

  // Deletar um paciente
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    patientService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // Atualizar um paciente
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody PatientDTO patientDTO, @PathVariable Long id) {
    PatientModel patient = patientService.fromDTO(patientDTO);
    patient.setIdPatient(id);
    patient = patientService.update(patient);
    return ResponseEntity.noContent().build();
  }
}