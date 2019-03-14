package com.example.cs4500_sp19_random1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_random1.models.FrequentlyAskedAnswer;

import com.example.cs4500_sp19_random1.repositories.FrequentlyAskedQuestionRepository;
import com.example.cs4500_sp19_random1.repositories.FrequentlyAskedAnswerRepository;

@RestController
@CrossOrigin(origins="*")
public class FrequentlyAskedQuestionService {
  @Autowired
  FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;
  @Autowired
  FrequentlyAskedAnswerRepository frequentlyAskedAnswerRepository;

  @GetMapping("/api/faqs")
  public List<FrequentlyAskedQuestion> findAllFAQs() {
    return frequentlyAskedQuestionRepository.findAllFrequentQuestions();
  }

  @GetMapping("/api/faqs/filter")
  public List<FrequentlyAskedQuestion> filterAllFAQs(@RequestParam("title") String title,
                                                     @RequestParam("question") String question) {

    if(title == "") {
      title = null;
    }
    if(question == "") {
      question = null;
    }
    return frequentlyAskedQuestionRepository.filterAllFrequentQuestions(title, question);
  }

  @GetMapping("/api/faqs/{faqId}")
  public FrequentlyAskedQuestion findFAQById(
          @PathVariable("faqId") Integer id) {
    return frequentlyAskedQuestionRepository.findFAQById(id);
  }

  @GetMapping("/api/faqs/{faqId}/answers")
  public List<FrequentlyAskedAnswer> findAnswersByID(
          @PathVariable("faqId") Integer id) {
    FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findFAQById(id);
    return frequentlyAskedQuestion.getAnswers();
  }

  @PostMapping("/api/faqs")
  public FrequentlyAskedQuestion createFAQ(@RequestBody FrequentlyAskedQuestion frequentlyAskedQuestion) {
    return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
  }
  @PostMapping("/api/faqs/{faqId}/answers")
  public FrequentlyAskedAnswer addAnswer(@PathVariable("faqId") Integer id,
                                               @RequestBody FrequentlyAskedAnswer frequentlyAskedAnswer) {

    FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findFAQById(id);
    frequentlyAskedAnswer.setFrequentlyAskedQuestion(frequentlyAskedQuestion);
    frequentlyAskedAnswerRepository.save(frequentlyAskedAnswer);
    return frequentlyAskedAnswer;
  }

  @PostMapping("/api/faqs/{faqId}/answers/{ansId}")
  public List<FrequentlyAskedAnswer> addAnswerRelationship(@PathVariable("faqId") Integer idFAQ,
                                               @PathVariable("ansId") Integer idAnswer) {
    FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findFAQById(idFAQ);
    FrequentlyAskedAnswer frequentlyAskedAnswer = frequentlyAskedAnswerRepository.findAnswerById(idAnswer);
    frequentlyAskedQuestion.answeredQuestion(frequentlyAskedAnswer);
    return frequentlyAskedQuestion.getAnswers();
  }

  @PutMapping("/api/faqs/{faqId}")
  public FrequentlyAskedQuestion updateFAQ(
          @PathVariable("faqId") Integer id,
          @RequestBody FrequentlyAskedQuestion frequentlyAskedQuestionUpdates) {
    FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findFAQById(id);
    String updatedTitle = frequentlyAskedQuestionUpdates.getTitle();
    String updatedQuestion = frequentlyAskedQuestionUpdates.getQuestion();
    if(updatedTitle != null) {
      frequentlyAskedQuestion.setTitle(updatedTitle);
    }
    if(updatedQuestion != null) {
      frequentlyAskedQuestion.setQuestion(updatedQuestion);
    }
    return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
  }
  @DeleteMapping("/api/faqs/{faqId}")
  public void deleteFrequentlyAskedQuestion(
          @PathVariable("faqId") Integer id) {
    frequentlyAskedQuestionRepository.deleteById(id);
  }

  @DeleteMapping("/api/faqs/{faqId}/answers/{ansId}")
  public void deleteAnswerRelationship(@PathVariable("faqId") Integer idFAQ,
                                                     @PathVariable("ansId") Integer idAnswer) {
    FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findFAQById(idFAQ);
    FrequentlyAskedAnswer frequentlyAskedAnswer = frequentlyAskedAnswerRepository.findAnswerById(idAnswer);
    frequentlyAskedQuestion.removeAnswer(frequentlyAskedAnswer);
    frequentlyAskedAnswer.setFrequentlyAskedQuestion(null);
  }
}

