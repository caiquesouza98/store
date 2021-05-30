package com.restapp.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.api.model.Produtos;
import com.restapp.api.model.ProdutosRepository;

@RestController
public class ProdutosController {

  private final ProdutosRepository repository;

  public ProdutosController(ProdutosRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/produtos")
  public List<Produtos> all() {
    return repository.findAll();
  }

  @GetMapping("/produtos/{id}")
  public Produtos one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
  }

  @PostMapping("/produtos")
  public Produtos newProduto(@RequestBody Produtos produto) {
    return repository.save(produto);
  }

  @PutMapping("/produtos/{id}")
  public Produtos replaceProduto(@PathVariable Long id, @RequestBody Produtos newProduto) {
    return repository.findById(id).map(produto -> {
      produto.setName(newProduto.getName());
      produto.setCategoria(newProduto.getCategoria());
      produto.setPrice(newProduto.getPrice());
      produto.setSize(newProduto.getSize());
      return repository.save(produto);
    }).orElseGet(() -> {
      newProduto.setId(id);
      return repository.save(newProduto);
    });
  }

  @DeleteMapping("/produtos/{id}")
  public void deleteProduto(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
