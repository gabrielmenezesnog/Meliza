package com.gmnsystems.meliza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmnsystems.meliza.models.HistoryModel;
import com.gmnsystems.meliza.services.HistoryService;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {

  @Autowired
  private HistoryService historyService;

  // Buscar todos os históricos
  // pelo mais recente
  @GetMapping
  public ResponseEntity<List<HistoryModel>> findAll() {
    List<HistoryModel> historyList = historyService.findAll();
    return ResponseEntity.ok().body(historyList);
  }
}
