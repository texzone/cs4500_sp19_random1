package com.example.cs4500_sp19_random1.services;


import java.util.List;

import com.example.cs4500_sp19_random1.models.Service;
import com.example.cs4500_sp19_random1.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_random1.models.User;
import com.example.cs4500_sp19_random1.repositories.UserRepository;


@RestController
@CrossOrigin(origins="*")
public class UserService {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/api/users")
  public List<User> findAllUser() {
    return userRepository.findAllUsers();
  }
  @GetMapping("/api/users/{userId}")
  public User findUserById(
          @PathVariable("userId") Integer id) {
    return userRepository.findUserById(id);
  }
  @PostMapping("/api/users")
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }
  @PutMapping("/api/users/{userId}")
  public User updateUser(
          @PathVariable("userId") Integer id,
          @RequestBody User userUpdates) {
    User user = userRepository.findUserById(id);
    user.setRole(userUpdates.getRole());
    user.setFirstName(userUpdates.getFirstName());
    user.setLastName(userUpdates.getLastName());
    return userRepository.save(user);
  }
  @DeleteMapping("/api/users/{userId}")
  public void deleteUser(
          @PathVariable("userId") Integer id) {
    userRepository.deleteById(id);
  }
}
