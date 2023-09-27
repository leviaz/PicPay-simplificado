package com.picpaysimplificado.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.shared.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {
  // class to handle errors from exception
  // last layer of treatment after the exceptions

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ExceptionDTO> threatDuplicateEntry(DataIntegrityViolationException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO("This Id already been registered", "400");
    return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> threat404(EntityNotFoundException exception) {

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDTO> threatGeneralExceptions(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
    return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
