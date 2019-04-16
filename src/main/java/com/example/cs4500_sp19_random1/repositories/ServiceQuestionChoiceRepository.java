package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.ServiceQuestionChoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceQuestionChoiceRepository extends CrudRepository<ServiceQuestionChoice, Integer> {
    List<ServiceQuestionChoice> findAllByServiceQuestionId(Integer id);
}