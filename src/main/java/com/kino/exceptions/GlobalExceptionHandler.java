package com.kino.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFound(EntityNotFoundException e) {
    return new ErrorResponse(e.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidation(ValidationException e) {
    return new ErrorResponse(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBeanValidation(MethodArgumentNotValidException e) {

    String message = Objects.requireNonNull(e.getBindingResult()
                    .getFieldError())
            .getDefaultMessage();

    return new ErrorResponse(message);
  }
}