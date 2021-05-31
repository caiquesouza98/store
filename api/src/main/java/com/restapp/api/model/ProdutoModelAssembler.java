package com.restapp.api.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.restapp.api.controller.ProdutosController;

@Component
public class ProdutoModelAssembler implements RepresentationModelAssembler<Produtos, EntityModel<Produtos>> {

  @Override
  public EntityModel<Produtos> toModel(Produtos produto) {

    EntityModel<Produtos> produtoModel = EntityModel.of(produto,
        linkTo(methodOn(ProdutosController.class).one(produto.getId())).withSelfRel(),
        linkTo(methodOn(ProdutosController.class).all()).withRel("produto"));

    return produtoModel;
  }

}
