package com.example.cs4500_sp19_random1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_random1.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  @Query(value="SELECT user FROM User user")
  public List<User> findAllUsers();
  @Query(value="SELECT user FROM User user WHERE user.id=:id")
  public User findUserById(@Param("id") Integer id);
  @Query(value="SELECT user FROM User user WHERE user.username=:username")
  public User findByUsername(@Param("username") String username);
  @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
  public List<User> findByCredentials(@Param("username") String username, @Param("password") String password);
}