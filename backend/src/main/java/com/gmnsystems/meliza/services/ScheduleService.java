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

import com.gmnsystems.meliza.dto.ScheduleDTO;
import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.repositories.ScheduleRepository;
import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class ScheduleService {

  @Autowired
  private ScheduleRepository scheduleRepository;

  // Buscar todas as agendas
  public List<ScheduleModel> getSchedules() {
    return scheduleRepository.findAll(Sort.by(Direction.ASC, "name"));
  }

  // Buscar uma agenda pelo id
  public ScheduleModel findById(Long id) {
    Optional<ScheduleModel> schedule = scheduleRepository.findById(id);
    return schedule.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Criar uma nova agenda
  public ScheduleModel createSchedule(ScheduleModel schedule) {
    LocalDate dateCreated = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    schedule.setDateCreated(dateCreated);
    scheduleRepository.save(schedule);

    return schedule;
  }

  // Atualizar um paciente
  public ScheduleModel update(ScheduleModel schedule) {
    try {
      // Diferente de findById, o método abaixo não busca
      // diretamente do banco de dados. Apenas prepara o
      // objeto para que seja monitorado e depois inserido
      // no banco de dados.
      ScheduleModel entity = scheduleRepository.getReferenceById(schedule.getIdSchedule());
      updateData(entity, schedule);
      return scheduleRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(schedule.getIdSchedule());
    }
  }

  // Método que atualiza a entidade existente com base na nova
  private void updateData(ScheduleModel entity, ScheduleModel schedule) {
    entity.setName(schedule.getName());
  }

  // Deletar uma agenda
  public void delete(Long id) {
    try {
      scheduleRepository.deleteById(id);
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public ScheduleModel fromDTO(ScheduleDTO scheduleDTO) {
    return new ScheduleModel(scheduleDTO.getIdSchedule(), scheduleDTO.getName(), scheduleDTO.getDateCreated(),
        scheduleDTO.getUser());
  }
}
