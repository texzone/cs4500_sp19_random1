package com.example.cs4500_sp19_random1.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="services")
public class Service {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String serviceName;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="PROVIDERS_SERVICES",
            joinColumns=@JoinColumn(name="SERVICE_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"))
    private List<ServiceProvider> providers;
    @ManyToMany(mappedBy="services")
    private List<ServiceCategory> serviceCategories;
    public List<ServiceCategory> getServiceCategories() {
        return serviceCategories;
    }
    public void setServiceCategories(List<ServiceCategory> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }
    public List<ServiceProvider> getProviders() {
        return providers;
    }
    public void setProviders(List<ServiceProvider> providers) {
        this.providers = providers;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String title) {
        this.serviceName = title;
    }
}
