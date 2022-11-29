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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.dto.ConsultationDTO;
import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.models.enums.ConsultationStatus;
import com.gmnsystems.meliza.repositories.ConsultationRepository;
import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class ConsultationService {

  @Autowired
  private ConsultationRepository consultationRepository;

  // Listar todas as consultas
  public List<ConsultationModel> getConsultations() {
    return consultationRepository.findAll(Sort.by(Direction.ASC, "consultationTime"));
  }

  // Buscar consulta pelo id
  public ConsultationModel findById(Long id) {
    Optional<ConsultationModel> consultation = consultationRepository.findById(id);
    return consultation.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Criar uma consulta
  public ConsultationModel createConsultation(ConsultationModel consultation) {

    // Capturar data referente ao registro do paciente
    LocalDate dateCreated = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    consultation.setDateCreated(dateCreated);

    consultation.setConsultationStatus(ConsultationStatus.WAITING_CONFIRMATION);

    // Verificando se a consulta criada contém
    // um paciente que já possui uma consulta
    // Se sim, a consulta é deletada automaticamente
    for (ConsultationModel item : consultationRepository.findAll()) {
      if (item.getPatient() == consultation.getPatient()) {
        consultationRepository.delete(consultation);
      } else if (item.getPatient() != consultation.getPatient()) {
        consultationRepository.save(consultation);
      }
    }
    return consultation;
  }

  // Atualizar uma consulta
  public ConsultationModel update(ConsultationModel consultation) {
    try {
      ConsultationModel entity = consultationRepository.getReferenceById(consultation.getIdConsultation());
      updateData(entity, consultation);
      return consultationRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(consultation.getIdConsultation());
    }
  }

  // Método que atualiza a entidade existente com base
  // na nova
  private void updateData(ConsultationModel entity, ConsultationModel consultation) {
    if (consultation.getConsultationDate() != null)
      entity.setConsultationDate(consultation.getConsultationDate().toString());
    if (consultation.getConsultationTime() != null)
      entity.setConsultationTime(consultation.getConsultationTime());
    if (consultation.getConsultationStatus() != null)
      entity.setConsultationStatus(consultation.getConsultationStatus());
    if (consultation.getPatient() != null)
      entity.setPatient(consultation.getPatient());
    if (consultation.getSchedule() != null)
      entity.setSchedule(consultation.getSchedule());
  }

  // Deletar uma consulta
  public void delete(Long id) {
    try {
      consultationRepository.deleteById(id);
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public ConsultationModel fromDTO(ConsultationDTO consultationDTO) {
    return new ConsultationModel(consultationDTO.getIdConsultation(), consultationDTO.getConsultationDate(),
        consultationDTO.getConsultationTime(), consultationDTO.getDateCreated(),
        consultationDTO.getConsultationStatus(), consultationDTO.getPatient(), consultationDTO.getSchedule(),
        consultationDTO.getUser());
  }
}
