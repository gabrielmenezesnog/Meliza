package com.gmnsystems.meliza.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.dto.SubscriptionDTO;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.models.enums.SubscriptionStatus;
import com.gmnsystems.meliza.repositories.SubscriptionRepository;
import com.gmnsystems.meliza.services.exceptions.DuplicateEntryException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class SubscriptionService {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private PlanService planService;

  // Buscar todas as assinaturas
  public List<SubscriptionModel> findAll() {
    return subscriptionRepository.findAll();
  }

  // Buscar uma assinatura pelo id
  public SubscriptionModel findById(Long id) {
    Optional<SubscriptionModel> subscription = subscriptionRepository.findById(id);
    return subscription.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Criar uma nova assinatura
  public SubscriptionModel create(SubscriptionModel subscription) {

    // capturando o id do plano referenciado em subscription
    PlanModel plan = planService.findById(subscription.getPlan().getIdPlan());

    // Setando o dia atual para determinar a data de início
    // da assinatura
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

    // Duração da assiantura baseada em today + duração em meses
    // do plano
    Integer duration = plan.getDurationInMonths();

    // setar data de início e fim
    subscription.setStartDate(today);
    subscription.setExpirationDate(today.plusMonths(duration));

    // Setando status inicial da inscrição
    subscription.setSubscriptionStatus(SubscriptionStatus.ACTIVE);

    try {
      subscriptionRepository.save(subscription);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEntryException();
    }
    return subscription;
  }

  // atualizar uma assinatura
  public SubscriptionModel update(SubscriptionModel subscription) {
    try {
      // Diferente de findById, o método abaixo não busca
      // diretamente do banco de dados. Apenas prepara o
      // objeto para que seja monitorado e depois inserido
      // no banco de dados.
      SubscriptionModel entity = subscriptionRepository.getReferenceById(subscription.getIdSubscription());
      updateData(entity, subscription);
      return subscriptionRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(subscription.getIdSubscription());
    }
  }

  // Método que atualiza a entidade existente
  // com base na nova
  private void updateData(SubscriptionModel entity, SubscriptionModel subscription) {
    if (subscription.getSubscriptionStatus() != null)
      entity.setSubscriptionStatus(subscription.getSubscriptionStatus());
    if (subscription.getPlan() != null)
      entity.setPlan(subscription.getPlan());
  }

  public SubscriptionModel fromDTO(SubscriptionDTO subscriptionDTO) {
    return new SubscriptionModel(subscriptionDTO.getIdSubscription(), subscriptionDTO.getStartDate(),
        subscriptionDTO.getExpirationDate(), subscriptionDTO.getSubscriptionStatus(), subscriptionDTO.getPatient(),
        subscriptionDTO.getPlan(), subscriptionDTO.getUser());
  }

}
