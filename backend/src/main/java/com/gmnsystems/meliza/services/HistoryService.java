package com.gmnsystems.meliza.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.models.HistoryModel;
import com.gmnsystems.meliza.repositories.HistoryRepository;

@Service
public class HistoryService {

  @Autowired
  private HistoryRepository historyRepository;

  // Buscar todos os históricos
  // pelo mais recente
  public List<HistoryModel> findAll() {
    return historyRepository.findAll(Sort.by(Direction.DESC, "idHistory"));
  }

  // Método para criar uma mensagem e salvá-la
  // no histórico
  public HistoryModel message(String message) {

    // Variável com o dia de hoje
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

    // Variável com o horário atual
    LocalTime now = LocalTime.ofInstant(Instant.now(), ZoneId.systemDefault());

    HistoryModel activity = new HistoryModel(
        null,
        message,
        today,
        now);

    historyRepository.save(activity);
    return activity;
  }
}
