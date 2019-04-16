package com.example.cs4500_sp19_random1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serviceName;
    @OneToMany
    private List<ServiceProvider> providers;
    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private List<ServiceCategory> serviceCategories;

    @OneToMany(mappedBy = "service", orphanRemoval = true)
    @JsonIgnore
    private List<ServiceQuestion> serviceQuestions;

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

    public List<ServiceQuestion> getServiceQuestions() {
        return serviceQuestions;
    }

    public void setServiceQuestions(List<ServiceQuestion> serviceQuestions) {
        this.serviceQuestions = serviceQuestions;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}