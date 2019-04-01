package com.example.cs4500_sp19_random1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_random1.models.ServiceProvider;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Integer> {
  @Query(value="SELECT provider FROM ServiceProvider provider")
  public List<ServiceProvider> findAllServiceProviders();
  @Query(value="SELECT provider FROM ServiceProvider provider WHERE provider.id=:id")
  public ServiceProvider findServiceProviderById(@Param("id") Integer id);
  @Query(value="SELECT provider FROM ServiceProvider provider WHERE provider.name=:username")
  public ServiceProvider findByServiceProvidername(@Param("username") String username);
  @Query(value="SELECT provider FROM ServiceProvider provider WHERE (:provider is null or provider.name LIKE :provider%) AND " +
          "(:zipCode is null or provider.zipCode=:zipCode)")
  public List<ServiceProvider> filterAllServiceProviders(@Param("provider") String provider,
                                                                  @Param("zipCode") String zipCode);
}