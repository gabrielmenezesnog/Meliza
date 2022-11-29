package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.PlanModel;

public interface PlanRepository extends JpaRepository<PlanModel, Long> {
}
