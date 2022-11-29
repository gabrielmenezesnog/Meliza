package com.gmnsystems.meliza.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.DuplicateEntryException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

// Anotação que intercepta as exceções para
// que este objeto possa executar um possível
// tratamento
@ControllerAdvice
public class ResourceExceptionHandler {

  // A exceção interceptava deve ir para resourceNotFound
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    String error = "Resource not found";
    HttpStatus status = HttpStatus.NOT_FOUND;

    StandardError standardError = new StandardError(
        Instant.now(),
        status.value(),
        error,
        e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(status).body(standardError);
  }

  // A exceção interceptava deve ir para database
  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;

    StandardError standardError = new StandardError(
        Instant.now(),
        status.value(),
        error,
        e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(status).body(standardError);
  }

  @ExceptionHandler(DuplicateEntryException.class)
  public ResponseEntity<StandardError> duplicateEntry(DuplicateEntryException e, HttpServletRequest request) {
    String error = "duplicate entry";
    HttpStatus status = HttpStatus.ALREADY_REPORTED;

    StandardError standardError = new StandardError(
        Instant.now(),
        status.value(),
        error,
        e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(status).body(standardError);
  }
}
