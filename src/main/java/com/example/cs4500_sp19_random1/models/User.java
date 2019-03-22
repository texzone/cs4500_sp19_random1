package com.example.cs4500_sp19_random1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    @OneToMany(mappedBy = "provider")
    private List<ServiceAnswer> serviceAnswers;
    @OneToMany(mappedBy = "user")
    private List<FrequentlyAskedAnswer> frequentlyAskedAnswers;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "PROVIDERS_SERVICES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID"))
    private List<Service> services;

    public User() {
    }

    public User(Integer id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<FrequentlyAskedAnswer> getFrequentlyAskedAnswers() {
        return frequentlyAskedAnswers;
    }

    public void setFrequentlyAskedAnswers(List<FrequentlyAskedAnswer> frequentlyAskedAnswers) {
        this.frequentlyAskedAnswers = frequentlyAskedAnswers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
      return role;
    }
    public void setRole(String role) {
      this.role = role;
    }
}
