package com.restapp.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.api.model.Categoria;
import com.restapp.api.model.CategoriaRepository;

@RestController
public class CategoriaController {

  private CategoriaRepository repository;

  public CategoriaController(CategoriaRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/categoria")
  public List<Categoria> all() {
    return repository.findAll();
  }

  @GetMapping("/categoria/{id}")
  public Categoria one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
  }

  @PostMapping("/categoria")
  public Categoria newCategoria(@RequestBody Categoria newCategoria) {
    return repository.save(newCategoria);
  }

  @PutMapping("/categoria/{id}")
  public Categoria replaceCategoria(@RequestBody Categoria newCategoria, @PathVariable Long id) {
    return repository.findById(id).map(categoria -> {
      categoria.setName(newCategoria.getName());
      return repository.save(categoria);
    }).orElseGet(() -> {
      newCategoria.setId(id);
      return repository.save(newCategoria);
    });
  }

  @DeleteMapping("/categoria/{id}")
  public void deleteCateogia(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
