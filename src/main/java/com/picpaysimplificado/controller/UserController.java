package com.picpaysimplificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.service.UserService;
import com.picpaysimplificado.shared.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    User user = userService.createUser(userDTO);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) throws Exception {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) throws Exception {
    User user = userService.findUserById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);

  }
}
