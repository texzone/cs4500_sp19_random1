package com.example.cs4500_sp19_random1.dtos;

import com.example.cs4500_sp19_random1.models.Service;
import com.example.cs4500_sp19_random1.models.ServiceProvider;
import com.example.cs4500_sp19_random1.models.ServiceQuestion;

import java.util.List;

public class ServiceDTO {

    private Integer id;
    private String serviceName;
    private List<ServiceProvider> serviceProviders;
    private List<ServiceQuestion> serviceQuestions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public List<ServiceQuestion> getServiceQuestions() {
        return serviceQuestions;
    }

    public void setServiceQuestions(List<ServiceQuestion> serviceQuestions) {
        this.serviceQuestions = serviceQuestions;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", serviceProviders=" + serviceProviders +
                ", serviceQuestions=" + serviceQuestions +
                '}';
    }

    public ServiceDTO fromEntity(Service service) {
        this.id = service.getId();
        this.serviceName = service.getServiceName();
        this.serviceProviders = service.getProviders();
        this.serviceQuestions = service.getServiceQuestions();
        return this;
    }

}