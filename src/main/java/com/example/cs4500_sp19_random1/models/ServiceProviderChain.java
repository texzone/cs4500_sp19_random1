package com.example.cs4500_sp19_random1.models;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="service_provider_chains")
public class ServiceProviderChain {

  @EmbeddedId
  private String id;

  @ManyToOne
  @MapsId("service_id")
  private Integer serviceID;

  private Service service;

  @ManyToOne
  @MapsId("provider_id")
  private Integer providerID;

  private ServiceProvider serviceProvider;



  public ServiceProviderChain(ServiceProvider sp, Service service) {
    this.serviceProvider = sp;
    this.service = service;
    this.id = Integer.toString(this.serviceProvider.getId()) + Integer.toString(this.service.getId());
    this.serviceID = this.service.getId();
    this.providerID = this.serviceProvider.getId();
  }
}