package com.example.cs4500_sp19_random1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_random1.models.ServiceProvider;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Integer> {
  @Query(value="SELECT provider FROM ServiceProvider provider")
  public List<ServiceProvider> findAllServiceProviders();
  @Query(value="SELECT provider FROM ServiceProvider provider WHERE provider.id=:id")
  public ServiceProvider findServiceProviderById(@Param("id") Integer id);
  @Query(value="SELECT provider FROM ServiceProvider provider WHERE provider.username=:username")
  public ServiceProvider findByServiceProvidername(@Param("username") String username);
}