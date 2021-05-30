package com.restapp.api.controller;

public class ProdutoNotFoundException extends RuntimeException {

  public ProdutoNotFoundException(Long id) {
    super("Não foi possível achar o produto " + id);
  }
}
