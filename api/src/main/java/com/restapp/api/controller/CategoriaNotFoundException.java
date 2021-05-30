package com.restapp.api.controller;

public class CategoriaNotFoundException extends RuntimeException {

  public CategoriaNotFoundException(Long id) {
    super("Não foi possível encontrar categoria " + id);
  }

}
