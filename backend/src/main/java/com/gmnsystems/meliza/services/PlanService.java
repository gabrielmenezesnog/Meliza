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

import com.gmnsystems.meliza.dto.PlanDTO;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.repositories.PlanRepository;
import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class PlanService {

  @Autowired
  private PlanRepository planRepository;

  // Buscar todos os planos
  public List<PlanModel> getPlans() {
    return planRepository.findAll();
  }

  // Buscar um plano pelo id
  public PlanModel findById(Long id) {
    Optional<PlanModel> plan = planRepository.findById(id);
    return plan.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Cadastrar novo plano
  public PlanModel createPlan(PlanModel plan) {

    // Capturar data referente ao registro do plano
    LocalDate dateCreated = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    plan.setDateCreated(dateCreated);

    planRepository.save(plan);

    return plan;
  }

  // Atualizar um plano
  public PlanModel update(PlanModel plan) {
    try {
      // Diferente de findById, o método abaixo não busca
      // diretamente do banco de dados. Apenas prepara o
      // objeto para que seja monitorado e depois inserido
      // no banco de dados.
      PlanModel entity = planRepository.getReferenceById(plan.getIdPlan());
      updateData(entity, plan);
      return planRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(plan.getIdPlan());
    }
  }

  // Método que atualiza a entidade existente com base na nova
  private void updateData(PlanModel entity, PlanModel plan) {

    if (plan.getName() != null)
      entity.setName(plan.getName());
    if (plan.getMaxConsultations() != null)
      entity.setMaxConsultations(plan.getMaxConsultations());
    if (plan.getPrice() != null)
      entity.setPrice(plan.getPrice());
    if (plan.getDurationInMonths() != null)
      entity.setDurationInMonths(plan.getDurationInMonths());
  }

  // Deletar um plano
  // É possível deletar um plano somente se
  // não houver assinatura nenhuma realizada para ele
  public void delete(Long id) {
    try {
      planRepository.deleteById(id);
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public PlanModel fromDTO(PlanDTO planDTO) {
    return new PlanModel(planDTO.getIdPlan(), planDTO.getName(), planDTO.getMaxConsultations(), planDTO.getPrice(),
        planDTO.getDurationInMonths(), planDTO.getDateCreated(), planDTO.getUser());
  }
}