package ru.ifmo.volunteer.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  @ExceptionHandler(value = {ResourceNotFoundException.class, EmptyResultDataAccessException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handle(final RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}
