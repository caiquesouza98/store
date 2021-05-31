package com.restapp.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.api.model.Pedido;
import com.restapp.api.model.Pedido.Status;
import com.restapp.api.model.PedidoModelAssembler;
import com.restapp.api.model.PedidoRepository;

@RestController
public class PedidoController {

  private final PedidoRepository repository;
  private final PedidoModelAssembler assembler;

  public PedidoController(PedidoRepository repository, PedidoModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/pedido")
  public CollectionModel<EntityModel<Pedido>> all() {
    List<EntityModel<Pedido>> pedido = repository.findAll().stream()
        .map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(pedido,
        linkTo(methodOn(PedidoController.class).all()).withSelfRel());
  }

  @PostMapping("/pedido")
  public ResponseEntity<EntityModel<Pedido>> newPedido(@RequestBody Pedido newPedido) {
    Pedido pedido = repository.save(newPedido);
    return ResponseEntity
        .created(linkTo(methodOn(PedidoController.class)
        .one(pedido.getId())).toUri()).body(assembler.toModel(pedido));
  }

  @GetMapping("/pedido/{id}")
  public EntityModel<Pedido> one(@PathVariable Long id) {
    Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));
    return assembler.toModel(pedido);
  }

  @PutMapping("/pedido/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable Long id) {
    Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

    if(pedido.getStatus() == Status.EM_ANDAMENTO) {
      pedido.setStatus(Status.FINALIZADO);
      return ResponseEntity.ok(assembler.toModel(repository.save(pedido)));
    }

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .header(HttpHeaders.CONTENT_TYPE,
               MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create().withTitle("Method not allowed")
            .withDetail("Você não pode finalizar um pedido que já está finalizado"));
  }

  @DeleteMapping("/pedido/{id}/cancel")
  public ResponseEntity<?> cancel(@PathVariable Long id) {
    Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

    if(pedido.getStatus() == Status.EM_ANDAMENTO) {
      pedido.setStatus(Status.CANCELADO);
      return ResponseEntity.ok(assembler.toModel(repository.save(pedido)));
    }

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .header(HttpHeaders.CONTENT_TYPE,
               MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create().withTitle("Method not allowed")
            .withDetail("Você não pode cancelar um pedido que já está cancelado"));
  }
}
