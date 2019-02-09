package com.example.cs4500_sp19_random1.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;


@Entity
@Table(name = "service_providers")
public class ServiceProvider extends User {
  @OneToMany(mappedBy = "service_provider")
  private List<Service> services;

  public ServiceProvider(Integer i, String username, String firstName, String lastName, List<Service> services) {
    super(i, username, firstName, lastName);
    this.services = services;
  }

  public List<Service> getServices() {
    return this.services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }
}
