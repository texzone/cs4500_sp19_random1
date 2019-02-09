package com.example.cs4500_sp19_random1.repositories;

import java.util.List;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FrequentlyAskedAnswerRepository extends CrudRepository<FrequentlyAskedAnswer, Integer> {
    @Query(value="SELECT answer FROM FrequentlyAskedAnswer answer")
    public List<FrequentlyAskedAnswer> findAllFrequentlyAskedAnswer();
    @Query(value="SELECT answer FROM FrequentlyAskedAnswer answer WHERE answer.id=:id")
    public FrequentlyAskedAnswer findAnswerById(@Param("id") Integer id);
}
