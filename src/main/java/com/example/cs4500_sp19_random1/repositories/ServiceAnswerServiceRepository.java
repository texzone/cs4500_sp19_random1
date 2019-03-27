package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.ServiceAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceAnswerServiceRepository extends CrudRepository<ServiceAnswer, Integer> {

    List<ServiceAnswer> findAllServiceAnswers();

    ServiceAnswer findServiceAnswerById(Integer qid);

}
