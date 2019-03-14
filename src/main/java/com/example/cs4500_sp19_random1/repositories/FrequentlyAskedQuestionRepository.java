package com.example.cs4500_sp19_random1.repositories;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FrequentlyAskedQuestionRepository extends
        CrudRepository<FrequentlyAskedQuestion, Integer> {

  @Query(value="SELECT frequentQuestions FROM FrequentlyAskedQuestion frequentQuestions")
  public List<FrequentlyAskedQuestion> findAllFrequentQuestions();

  @Query(value="SELECT frequentQuestions FROM FrequentlyAskedQuestion frequentQuestions WHERE (:title is null or frequentQuestions.title LIKE :title%) AND " +
          "(:question is null or frequentQuestions.question LIKE :question%)")
  public List<FrequentlyAskedQuestion> filterAllFrequentQuestions(@Param("title") String title,
                                                                  @Param("question") String question);

  @Query(value="SELECT frequentQuestions FROM FrequentlyAskedQuestion frequentQuestions WHERE frequentQuestions.id=:id")
  public FrequentlyAskedQuestion findFAQById(@Param("id") Integer id);

}

