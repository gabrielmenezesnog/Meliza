package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.ScheduleModel;

public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {
}
