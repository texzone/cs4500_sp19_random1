package com.example.cs4500_sp19_random1.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;


@Entity
@Table(name = "service_providers")
public class ServiceProvider {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  @OneToMany(mappedBy = "providers")
  private List<Service> services;
  private String name;
  private String zipCode;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {return this.name;}

  public String getZipCode() {
    return zipCode;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public List<Service> getServices() {
    return this.services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }
}
