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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gmnsystems.meliza.dto.PlanDTO;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.services.PlanService;
import com.gmnsystems.meliza.services.SubscriptionService;

@RestController
@RequestMapping(value = "/plans")
public class PlanController {

  @Autowired
  private PlanService planService;

  @Autowired
  private SubscriptionService subscriptionService;

  // Buscar todos os planos
  @GetMapping
  public List<PlanDTO> getPlans() {
    List<PlanModel> list = planService.getPlans();
    List<PlanDTO> listDto = list.stream().map(item -> new PlanDTO(item)).collect(Collectors.toList());
    return listDto;
  }

  // Buscar plano pelo id
  @GetMapping("/{id}")
  public ResponseEntity<PlanModel> findById(@PathVariable Long id) {
    PlanModel plan = planService.findById(id);
    return ResponseEntity.ok().body(plan);
  }

  // Adicionar um plano
  @PostMapping
  public ResponseEntity<Void> createPlan(@RequestBody PlanModel plan) {
    plan = planService.createPlan(plan);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(plan.getIdPlan()).toUri();
    return ResponseEntity.created(uri).build();
  }

  // Atualizar um plano
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody PlanDTO planDTO, @PathVariable Long id) {
    PlanModel plan = planService.fromDTO(planDTO);
    plan.setIdPlan(id);
    plan = planService.update(plan);
    return ResponseEntity.noContent().build();
  }

  // Deletar um plano
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    planService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // Endpoint para retornar as assinaturas do plano especificado
  @GetMapping("/{id}/subscriptions")
  public ResponseEntity<List<SubscriptionModel>> findSubscriptions(@PathVariable Long id) {
    PlanModel plan = planService.findById(id);
    List<SubscriptionModel> subscription = subscriptionService.findAll();

    // Acumulador que irá receber todas os planos do objeto
    List<SubscriptionModel> accumulator = new ArrayList<>();

    subscription.forEach(item -> {
      if (item.getPlan() == plan) {
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