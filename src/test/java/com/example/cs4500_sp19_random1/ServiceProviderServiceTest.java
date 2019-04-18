package com.example.cs4500_sp19_random1;



import com.example.cs4500_sp19_random1.models.Service;
import com.example.cs4500_sp19_random1.models.ServiceProvider;
import com.example.cs4500_sp19_random1.repositories.ServiceProviderRepository;
import com.example.cs4500_sp19_random1.services.ServiceProviderService;
import com.example.cs4500_sp19_random1.util.Address;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.*;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceProviderServiceTest {
  @InjectMocks
  private ServiceProviderService serviceProviderService;
  @Mock
  private ServiceProviderRepository serviceProviderRepository;

  ServiceProvider serviceProvider = new ServiceProvider();
  ServiceProvider serviceProvider2 = new ServiceProvider();
  List<ServiceProvider> allProviders = new ArrayList<>();
  List<ServiceProvider> oneProvider = new ArrayList<>();

  @Before
  public void setUp() {

    Address serviceProviderAddress = new Address("22 Smith St.","Boston", "MA", "02120");
    Address serviceProviderAddress2 = new Address("25 John St.","Boston", "MA", "12571");

    serviceProvider.setId(1);
    serviceProvider.setName("TestProvider");
    serviceProvider.setBusinessAddress(serviceProviderAddress);
    serviceProvider2.setId(2);
    serviceProvider2.setName("TestProvider2");
    serviceProvider2.setBusinessAddress(serviceProviderAddress2);

    allProviders.add(serviceProvider);
    allProviders.add(serviceProvider2);

    oneProvider.add(serviceProvider);

    Mockito.when(serviceProviderRepository.findAllServiceProviders()).
            thenReturn(allProviders);
    Mockito.when(serviceProviderRepository.findServiceProviderById(1)).
            thenReturn(serviceProvider);
    Mockito.when(serviceProviderRepository.findByServiceProvidername("TestProvider")).
            thenReturn(serviceProvider);
    Mockito.when(serviceProviderRepository.filterAllServiceProviders("TestProvider", "02120"))
            .thenReturn(oneProvider);

  }

  @Test
  public void findAllServiceProvidersTest() {
    List<ServiceProvider> found = serviceProviderService.findAllServiceProviders();
    assertEquals(allProviders, found);
  }

  @Test
  public void findServiceProviderByIdTest() {
    ServiceProvider found = serviceProviderService.findAllServiceProviders(1);
    assertEquals(serviceProvider, found);
  }

  @Test
  public void findServiceProviderByNameTest() {
    ServiceProvider found = serviceProviderService.findAllServiceProviders("TestProvider");
    assertEquals(serviceProvider, found);
  }

  @Test
  public void filterServiceProvidersTest() {
    List<ServiceProvider> found = serviceProviderService.filterAllServiceProviders("TestProvider", "02120");
    assertEquals(oneProvider, found);
  }

}
