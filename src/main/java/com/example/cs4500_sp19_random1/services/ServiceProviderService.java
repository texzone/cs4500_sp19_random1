package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.models.ServiceProvider;
import com.example.cs4500_sp19_random1.repositories.ServiceProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class ServiceProviderService {
  @Autowired
  ServiceProviderRepository serviceProviderRepository;
  @GetMapping("api/service_providers")
  public List<ServiceProvider> findAllServiceProviders() {
    return serviceProviderRepository.findAllServiceProviders();
  }
  @GetMapping("api/service_providers/{serviceProviderId}")
  public ServiceProvider findAllServiceProviders(
          @PathVariable("serviceProviderId") Integer id) {
    return serviceProviderRepository.findServiceProviderById(id);
  }
  @GetMapping("api/service_providers/name/{serviceProviderName}")
  public ServiceProvider findAllServiceProviders(
          @PathVariable("serviceProviderName") String name) {
    return serviceProviderRepository.findByServiceProvidername(name);
  }
  @GetMapping("/api/service_providers/filter")
  public List<ServiceProvider> filterAllServiceProviders(@RequestParam("provider") String provider,
                                                     @RequestParam("zipCode") String zipCode) {

    if(provider == "") {
      provider = null;
    }
    if(zipCode == "") {
      zipCode = null;
    }
    return serviceProviderRepository.filterAllServiceProviders(provider, zipCode);
  }
}
