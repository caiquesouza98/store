package com.restapp.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.api.model.Categoria;
import com.restapp.api.model.CategoriaModelAssembler;
import com.restapp.api.model.CategoriaRepository;

@RestController
public class CategoriaController {

  private CategoriaRepository repository;
  private CategoriaModelAssembler assembler;

  public CategoriaController(CategoriaRepository repository, CategoriaModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/categoria")
  public CollectionModel<EntityModel<Categoria>> all() {
    List<EntityModel<Categoria>> categoria = repository.findAll().stream()
        .map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(categoria,
        linkTo(methodOn(CategoriaController.class).all()).withSelfRel());
  }

  @GetMapping("/categoria/{id}")
  public EntityModel<Categoria> one(@PathVariable Long id) {
    Categoria categoria = repository.findById(id)
        .orElseThrow(() -> new CategoriaNotFoundException(id));
    return assembler.toModel(categoria);
  }

  @PostMapping("/categoria")
  public ResponseEntity<EntityModel<Categoria>> newCategoria(@RequestBody Categoria newCategoria) {
    Categoria categoria = repository.save(newCategoria);

    return ResponseEntity
        .created(linkTo(methodOn(CategoriaController.class)
        .one(categoria.getId())).toUri()).body(assembler.toModel(categoria));
  }

  @PutMapping("/categoria/{id}")
  public ResponseEntity<?> replaceCategoria(@RequestBody Categoria newCategoria, @PathVariable Long id) {
    return repository.findById(id).map(categoria -> {
      categoria.setName(newCategoria.getName());
      return ResponseEntity.ok(assembler.toModel(repository.save(newCategoria)));
    }).orElseGet(() -> {
      newCategoria.setId(id);
      return ResponseEntity.ok(assembler.toModel(repository.save(newCategoria)));
    });
  }

  @DeleteMapping("/categoria/{id}")
  public ResponseEntity<?> deleteCateogia(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
