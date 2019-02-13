package com.example.cs4500_sp19_random1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_random1.models.ServiceProviderChain;

public interface ServiceProviderChainRepository extends CrudRepository<ServiceProviderChain, Integer> {
  @Query(value="SELECT spchain FROM ServiceProviderChain spchain")
  public List<ServiceProviderChain> findAllSPChains();

  @Query(value="SELECT spchain FROM ServiceProviderChain spchain WHERE spchain.service.id=:serviceId")
  public List<ServiceProviderChain> findAllServiceSPChains(@Param("serviceId") Integer serviceId);

  @Query(value="SELECT spchain FROM ServiceProviderChain spchain WHERE spchain.serviceProvider.id=:providerId")
  public List<ServiceProviderChain> findAllProviderSPChains(@Param("providerId") Integer providerId);
}
