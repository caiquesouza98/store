package com.restapp.api.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.restapp.api.controller.PedidoController;
import com.restapp.api.model.Pedido.Status;

public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

  @Override
  public EntityModel<Pedido> toModel(Pedido pedido) {
    EntityModel<Pedido> pedidoModel = EntityModel.of(pedido,
        linkTo(methodOn(PedidoController.class).one(pedido.getId())).withSelfRel(),
        linkTo(methodOn(PedidoController.class).all()).withRel("pedido"));

    if(pedido.getStatus() == Status.EM_ANDAMENTO) {
      pedidoModel.add(linkTo(methodOn(PedidoController.class).cancel(pedido.getId())).withRel("cancel"));
      pedidoModel.add(linkTo(methodOn(PedidoController.class).complete(pedido.getId())).withRel("complete"));
    }

    return pedidoModel;
  }
}
