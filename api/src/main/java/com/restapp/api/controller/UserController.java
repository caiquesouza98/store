package com.restapp.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapp.api.model.User;
import com.restapp.api.model.UserRepository;

@RestController
public class UserController {

  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/user")
  List<User> all() {
    return repository.findAll();
  }

  @GetMapping("/user/{id}")
  User one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  @PostMapping("/user")
  User newEmployee(@RequestBody User user) {
    return repository.save(user);
  }

  @PutMapping("/user/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
    return repository.findById(id).map(employee -> {
      employee.setName(newUser.getName());
      employee.setRole(newUser.getRole());
      return repository.save(employee);
    }).orElseGet(() -> {
      newUser.setId(id);
      return repository.save(newUser);
    });
  }

  @DeleteMapping("/user/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
