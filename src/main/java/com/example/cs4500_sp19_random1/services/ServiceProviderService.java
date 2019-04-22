package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.models.ServiceProvider;
import com.example.cs4500_sp19_random1.repositories.ServiceProviderRepository;
import com.example.cs4500_sp19_random1.util.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;

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
    List<ServiceProvider> serviceProviders = serviceProviderRepository.filterAllServiceProviders(provider);
    if(!zipCode.equals("")) {
      Integer zipCodeInt = Integer.valueOf(zipCode);
      Collections.sort(serviceProviders, Comparator.comparingInt(x ->
              Math.abs(Integer.valueOf(x.getBusinessAddress().getZipCode()) - zipCodeInt)));
    }
    return serviceProviders;
  }
  @PutMapping("api/service_providers/{serviceProviderId}")
  public ServiceProvider updateServiceProviderById(
          @PathVariable("serviceProviderId") Integer id,
          @RequestBody ServiceProvider serviceProviderUpdates) {
    ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(id);

    // General Section
    String updatedName = serviceProviderUpdates.getName();
    Float updatedRating = serviceProviderUpdates.getRating();
    String updatedPrice = serviceProviderUpdates.getPrice();
    Integer updatedHires = serviceProviderUpdates.getHires();
    Integer updatedYear = serviceProviderUpdates.getYearFounded();
    Integer updatedNumEmployees = serviceProviderUpdates.getNumEmployees();
    String updatedEmail = serviceProviderUpdates.getBusinessEmail();
    Boolean updatedBackgroundCheck = serviceProviderUpdates.isBackgroundChecked();

    if(updatedName != null) { serviceProvider.setName(updatedName); }
    if(updatedPrice != null) { serviceProvider.setPrice(updatedPrice); }
    if(updatedYear != 0) { serviceProvider.setYearFounded(updatedYear); }
    if(updatedRating != 0.0) { serviceProvider.setRating(updatedRating); }
    if(updatedHires != 0) { serviceProvider.setHires(updatedHires); }
    if(updatedNumEmployees != 0) { serviceProvider.setNumEmployees(updatedNumEmployees); }
    if(updatedEmail != null) { serviceProvider.setBusinessEmail(updatedEmail); }
    serviceProvider.setBackgroundChecked(updatedBackgroundCheck);

    // Business Address section

    Address updatedAddress = serviceProviderUpdates.getBusinessAddress();
    // Business Address section is optional, could be null altogether.
    if(updatedAddress != null) {
      Address currentAddress = serviceProvider.getBusinessAddress();
      String updatedStreet = updatedAddress.getStreet();
      String updatedCity = updatedAddress.getCity();
      String updatedState = updatedAddress.getState();
      String updatedZipCode = updatedAddress.getZipCode();
      if (updatedStreet != null) {
        currentAddress.setStreet(updatedStreet);
      }
      if (updatedCity != null) {
        currentAddress.setCity(updatedCity);
      }
      if (updatedState != null) {
        currentAddress.setState(updatedState);
      }
      if (updatedZipCode != null) {
        currentAddress.setZipCode(updatedZipCode);
      }
      serviceProvider.setBusinessAddress(currentAddress);
    }

    // Payment methods section
    Boolean updatedCreditCard = serviceProviderUpdates.isCreditCard();
    Boolean updatedCash = serviceProviderUpdates.isCash();
    Boolean updatedCheck = serviceProviderUpdates.isCheck();
    Boolean updatedVenmo = serviceProviderUpdates.isVenmo();
    Boolean updatedSquare = serviceProviderUpdates.isSquare();
    Boolean updatedPaypal = serviceProviderUpdates.isPaypal();

    serviceProvider.setCreditCard(updatedCreditCard);
    serviceProvider.setCash(updatedCash);
    serviceProvider.setCheck(updatedCheck);
    serviceProvider.setVenmo(updatedVenmo);
    serviceProvider.setSquare(updatedSquare);
    serviceProvider.setPaypal(updatedPaypal);

    // Social media section
    String updatedFbLink = serviceProviderUpdates.getFacebookLink();
    String updatedInstaLink = serviceProviderUpdates.getInstaLink();
    String updatedTwitterLink = serviceProviderUpdates.getTwitterLink();
    if(updatedFbLink != null) { serviceProvider.setFacebookLink(updatedFbLink); }
    if(updatedInstaLink != null) { serviceProvider.setInstaLink(updatedInstaLink); }
    if(updatedTwitterLink != null) { serviceProvider.setTwitterLink(updatedTwitterLink); }

    return serviceProviderRepository.save(serviceProvider);
  }

}
