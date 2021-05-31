package com.restapp.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedido {

  private @Id @GeneratedValue Long id;
  private ArrayList<Produtos> produtos;
  private Date data;
  private Float desconto;
  private Float total;
  private Float precoFinal;
  private Status status;

  public Pedido() {
  }

  public Pedido(ArrayList<Produtos> produtos,
                Date data,
                Float desconto,
                Status status) {
    this.produtos = produtos;
    this.data = data;
    this.desconto = desconto;
    this.status = status;
    setTotal();
    setPrecoFinal();
  }

  public Long getId() {
    return id;
  }

  public ArrayList<Produtos> getProdutos() {
    return produtos;
  }

  public Date getData() {
    return data;
  }

  public Float getDesconto() {
    return desconto;
  }

  public Float getTotal() {
    return total;
  }

  public Float getPrecoFinal() {
    return precoFinal;
  }

  public Status getStatus() {
    return status;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProdutos(ArrayList<Produtos> produtos) {
    this.produtos = produtos;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public void setDesconto(Float desconto) {
    this.desconto = desconto;
  }

  private void setTotal() {
    for(Produtos p : produtos) {
      this.total += p.getPrice();
    }
  }

  private void setPrecoFinal() {
    this.precoFinal = total * (1-(desconto/100));
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, desconto, precoFinal, produtos, total, status);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Pedido))
      return false;
    Pedido other = (Pedido) obj;
    return Objects.equals(data, other.data)
        && Objects.equals(desconto, other.desconto)
        && Objects.equals(precoFinal, other.precoFinal)
        && Objects.equals(produtos, other.produtos)
        && Objects.equals(total, other.total)
        && Objects.equals(status, other.status);
  }

  @Override
  public String toString() {
    return "Pedido {produtos=" + produtos +
           ", data=" + data +
           ", desconto=" + desconto +
           ", total=" + total +
           ", precoFinal=" + precoFinal +
           ", status= "+ status + "}";
  }

  public enum Status {
    EM_ANDAMENTO,
    CANCELADO,
    FINALIZADO
  }
}
