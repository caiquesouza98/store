package com.restapp.api.controller;

public class PedidoNotFoundException extends RuntimeException{

  public PedidoNotFoundException(Long id) {
    super("Não foi possível achar o pedido" + id);
  }
}
