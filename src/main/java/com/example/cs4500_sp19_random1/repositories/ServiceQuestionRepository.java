package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.ServiceQuestion;
import org.springframework.data.repository.CrudRepository;

public interface ServiceQuestionRepository extends CrudRepository<ServiceQuestion, Integer> {

    ServiceQuestion findOneById(Integer id);

}