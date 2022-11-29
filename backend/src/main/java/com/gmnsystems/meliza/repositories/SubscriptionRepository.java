package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.SubscriptionModel;

public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, Long> {

}
