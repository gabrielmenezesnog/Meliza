package com.gmnsystems.meliza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmnsystems.meliza.models.HistoryModel;

public interface HistoryRepository extends JpaRepository<HistoryModel, Long> {

}
