package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.ServiceQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceQuestionRepository extends CrudRepository<ServiceQuestion, Integer> {
  @Query(value = "SELECT serviceQuestion FROM ServiceQuestion serviceQuestion")
  public List<ServiceQuestion> findAllServiceQuestion();

  @Query(value = "SELECT serviceQuestion FROM ServiceQuestion serviceQuestion WHERE serviceQuestion.id=:id")
  public ServiceQuestion findServiceQuestionById(@Param("id") Integer id);

  List<ServiceQuestion> findAllByServiceId(Integer id);
}