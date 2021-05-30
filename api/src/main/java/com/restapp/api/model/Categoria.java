package com.restapp.api.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {

  private @Id @GeneratedValue Long id;
  private String name;

  public Categoria() {
  }

  public Categoria(String name) {
    this.setName(name);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Categoria))
      return false;
    Categoria categoria = (Categoria) obj;
    return (Objects.equals(this.name, categoria.name)) && (Objects.equals(this.id, categoria.id));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return ("Categoria { id= " + this.id + ", name= " + this.name);
  }
}
