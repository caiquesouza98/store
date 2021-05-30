package com.restapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoriaNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(CategoriaNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String categoriaNotFoundHandler(CategoriaNotFoundException ex) {
    return ex.getMessage();
  }
}
