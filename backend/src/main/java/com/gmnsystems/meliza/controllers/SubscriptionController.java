package com.gmnsystems.meliza.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gmnsystems.meliza.dto.SubscriptionDTO;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.services.SubscriptionService;

@RestController
@RequestMapping(value = "/subscriptions")
public class SubscriptionController {

  @Autowired
  private SubscriptionService subscriptionService;

  // Buscar todas as assinaturas
  @GetMapping
  public ResponseEntity<List<SubscriptionModel>> getSubscriptions() {
    return ResponseEntity.ok().body(subscriptionService.findAll());
  }

  // Buscar assinatura pelo id
  @GetMapping("/{id}")
  public ResponseEntity<SubscriptionModel> findById(@PathVariable Long id) {
    SubscriptionModel subscription = subscriptionService.findById(id);
    return ResponseEntity.ok().body(subscription);
  }

  // Adicionar uma assinatura
  @PostMapping
  public ResponseEntity<Void> createPlan(@RequestBody SubscriptionDTO subscriptionDTO) {
    SubscriptionModel subscription = subscriptionService.fromDTO(subscriptionDTO);
    subscription = subscriptionService.create(subscription);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(subscription.getIdSubscription()).toUri();
    return ResponseEntity.created(uri).build();
  }

  // Atualizar uma assinatura
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody SubscriptionDTO subscriptionDTO, @PathVariable Long id) {
    SubscriptionModel subscription = subscriptionService.fromDTO(subscriptionDTO);
    subscription.setIdSubscription(id);
    subscription = subscriptionService.update(subscription);
    return ResponseEntity.noContent().build();
  }
}
