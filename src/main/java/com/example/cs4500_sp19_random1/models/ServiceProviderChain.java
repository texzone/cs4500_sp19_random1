package com.example.cs4500_sp19_random1.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="service_provider_chains")
public class ServiceProviderChain {
  @Id
  private String id;
  @ManyToOne
  private Service service;
  @ManyToOne
  private ServiceProvider serviceProvider;


  public ServiceProviderChain(ServiceProvider sp, Service service) {
    this.serviceProvider = sp;
    this.service = service;
    this.id = Integer.toString(this.serviceProvider.getId()) + Integer.toString(this.service.getId());
  }
}