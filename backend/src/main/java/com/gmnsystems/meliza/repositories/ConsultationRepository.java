package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.ConsultationModel;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {

}
