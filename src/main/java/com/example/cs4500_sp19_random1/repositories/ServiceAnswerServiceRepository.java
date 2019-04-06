package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.ServiceAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceAnswerServiceRepository extends CrudRepository<ServiceAnswer, Integer> {
    @Query(value="SELECT serviceAnswer FROM ServiceAnswer serviceAnswer")
    List<ServiceAnswer> findAllServiceAnswers();
    @Query(value="SELECT serviceAnswer FROM ServiceAnswer serviceAnswer WHERE serviceAnswer.id=:id")
    ServiceAnswer findServiceAnswerById(@Param("id") Integer id);
}
