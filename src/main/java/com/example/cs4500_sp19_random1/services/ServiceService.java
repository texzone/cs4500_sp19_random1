package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.dtos.ServiceDTO;
import com.example.cs4500_sp19_random1.models.Service;
import com.example.cs4500_sp19_random1.models.ServiceProvider;
import com.example.cs4500_sp19_random1.models.ServiceQuestion;
import com.example.cs4500_sp19_random1.repositories.ServiceProviderRepository;
import com.example.cs4500_sp19_random1.repositories.ServiceQuestionChoiceRepository;
import com.example.cs4500_sp19_random1.repositories.ServiceQuestionRepository;
import com.example.cs4500_sp19_random1.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ServiceService {
  @Autowired
  private
  ServiceRepository serviceRepository;
  @Autowired
  private
  ServiceProviderRepository serviceProviderRepository;
  @Autowired
  private ServiceQuestionRepository serviceQuestionRepository;
  @Autowired
  private ServiceQuestionChoiceRepository serviceQuestionChoiceRepository;

  @GetMapping("/api/services")
  public List<ServiceDTO> findAllService() {
    List<ServiceDTO> services = new ArrayList<>();
    List<Service> allServices = serviceRepository.findAllServices();

    allServices.forEach(service -> {
      service.setProviders(serviceProviderRepository.findAllByServiceId(service.getId()));
      services.add(new ServiceDTO().fromEntity(service));
    });

    for (Service allService : allServices) {
      for (ServiceQuestion serviceQuestion : allService.getServiceQuestions()) {
        serviceQuestion.setChoices(serviceQuestionChoiceRepository.findAllByServiceQuestionId(serviceQuestion.getId()));
      }
    }

    return services;
  }

  @GetMapping("/api/services/{serviceId}")
  public ServiceDTO findServiceById(
          @PathVariable("serviceId") Integer id) {
    Service serviceById = serviceRepository.findServiceById(id);

    List<ServiceProvider> allByServiceId = serviceProviderRepository.findAllByServiceId(id);

    serviceById.setProviders(allByServiceId);

    return new ServiceDTO().fromEntity(serviceById);
  }

  @PostMapping("/api/services")
  public Service createService(@RequestBody Service service) {
    return serviceRepository.save(service);
  }

  @PutMapping("/api/services/{serviceId}")
  public Service updateService(
          @PathVariable("serviceId") Integer id,
          @RequestBody Service serviceUpdates) {
    Service service = serviceRepository.findServiceById(id);
    service.setServiceName(serviceUpdates.getServiceName());
    return serviceRepository.save(service);
  }

  @DeleteMapping("/api/services/{serviceId}")
  public void deleteService(
          @PathVariable("serviceId") Integer id) {
    serviceRepository.deleteById(id);
  }
}