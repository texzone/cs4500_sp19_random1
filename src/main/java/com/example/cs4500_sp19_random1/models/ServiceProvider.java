package com.example.cs4500_sp19_random1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.List;


@Entity
public class ServiceProvider extends Provider {

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Service service;

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }
}