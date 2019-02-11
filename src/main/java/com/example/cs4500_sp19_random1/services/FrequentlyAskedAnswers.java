package com.example.cs4500_sp19_random1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedAnswer;

import com.example.cs4500_sp19_random1.repositories.FrequentlyAskedAnswerRepository;

@RestController
@CrossOrigin(origins="*")
public class FrequentlyAskedAnswers {
  @Autowired
  FrequentlyAskedAnswerRepository repository;
  @GetMapping("/api/faq-answers")
  public List<FrequentlyAskedAnswer> findAllFrequentlyAskedQuestions() {
    return repository.findAllFrequentlyAskedAnswer();
  }
  @GetMapping("/api/faq-answers/{id}")
  public FrequentlyAskedAnswer findFrequentlyAskedQuestionById(
          @PathVariable("id") Integer id) {
    return repository.findAnswerById(id);
  }
}
