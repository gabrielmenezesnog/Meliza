package com.gmnsystems.meliza.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.dto.PatientDTO;
import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.repositories.PatientRepository;
import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class PatientService {

  // Chamando paciente e plano para que possa
  // ser possível relacionar id's e gerar uma
  // assinatura de plano
  @Autowired
  private PatientRepository patientRepository;

  // Buscar todos os pacientes
  public List<PatientModel> findAll() {
    return patientRepository.findAll();
  }

  // Buscar paciente pelo id
  // Optional serve para evitar erros como:
  // id nulo e id inexistente
  public PatientModel findById(Long id) {
    Optional<PatientModel> patient = patientRepository.findById(id);
    return patient.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Registrar um paciente
  public PatientModel registerPatient(PatientModel patient) {

    // Capturar data referente ao registro do paciente
    LocalDate dateCreated = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    patient.setDateCreated(dateCreated);

    patientRepository.save(patient);

    return patient;
  }

  // Atualizar um paciente
  public PatientModel update(PatientModel patient) {
    try {
      // Diferente de findById, o método abaixo não busca
      // diretamente do banco de dados. Apenas prepara o
      // objeto para que seja monitorado e depois inserido
      // no banco de dados.
      PatientModel entity = patientRepository.getReferenceById(patient.getIdPatient());
      updateData(entity, patient);
      return patientRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(patient.getIdPatient());
    }
  }

  // Método que atualiza a entidade existente com base
  // na nova
  private void updateData(PatientModel entity, PatientModel patient) {
    if (patient.getFirstName() != null)
      entity.setFirstName(patient.getFirstName());
    if (patient.getLastName() != null)
      entity.setLastName(patient.getLastName());
    if (patient.getEmail() != null)
      entity.setEmail(patient.getEmail());
    if (patient.getPhone() != null)
      entity.setPhone(patient.getPhone());
    if (patient.getCpf() != null)
      entity.setCpf(patient.getCpf());
  }

  // Deletar um paciente
  public void delete(Long id) {
    try {
      patientRepository.deleteById(id);
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public PatientModel fromDTO(PatientDTO patientDTO) {
    return new PatientModel(patientDTO.getIdPatient(), patientDTO.getFirstName(), patientDTO.getLastName(),
        patientDTO.getEmail(), patientDTO.getPhone(), patientDTO.getCpf(), patientDTO.getDateCreated(),
        patientDTO.getUser());
  }
}