package com.restapp.api.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.restapp.api.controller.CategoriaController;

public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

  @Override
  public EntityModel<Categoria> toModel(Categoria categoria) {
    EntityModel<Categoria> categoriaModel = EntityModel.of(categoria,
        linkTo(methodOn(CategoriaController.class).one(categoria.getId())).withSelfRel(),
        linkTo(methodOn(CategoriaController.class).all()).withRel("categoria"));

    return categoriaModel;
  }
}
