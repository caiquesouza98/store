package com.restapp.api.model;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produtos {

  private @Id @GeneratedValue Long id;
  private String name;
  private Categoria categoria;
  private Float price;
  private ArrayList<Size> size;

  public Produtos() {
  }

  public Produtos(String name,
                  Categoria categoria,
                  Float price,
                  ArrayList<Size> size) {
    this.setName(name);
    this.setCategoria(categoria);
    this.setPrice(price);
    this.setSize(size);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public Float getPrice() {
    return price;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public ArrayList<Size> getSize() {
    return size;
  }

  public void setSize(ArrayList<Size> size) {
    this.size = size;
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoria, id, name, price);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Produtos))
      return false;
    Produtos other = (Produtos) obj;
    return Objects.equals(categoria, other.categoria) && Objects.equals(id, other.id)
        && Objects.equals(name, other.name) && Objects.equals(price, other.price);
  }

  @Override
  public String toString() {
    return "Produtos {id=" + id + ", name=" + name + ", categoria=" + categoria + ", price=" + price + "}";
  }

  enum Size {
    PP("PP"), P("P"), M("M"), G("G"), GG("GG"), XG("XG");

    private String size;

    Size(String size) {
      this.size = size;
    }

    public String getSize() {
      return size;
    }
  }
}
