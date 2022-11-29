package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.PatientModel;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {

}
