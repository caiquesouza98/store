package com.restapp.api.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

  private @Id @GeneratedValue Long id;
  private String name;
  private String role;

  public User() {
  }

  public User(String name, String role) {
    this.setName(name);
    this.setRole(role);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User user = (User) obj;
    return (Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
        && Objects.equals(this.role, user.role));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public String toString() {
    return ("Employee { id= " + this.id + ", name= " + this.name + ", role= " + this.role + " }");
  }
}
