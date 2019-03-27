package com.example.cs4500_sp19_random1;

import java.util.ArrayList;
import java.util.List;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedAnswer;
import com.example.cs4500_sp19_random1.models.FrequentlyAskedQuestion;
import com.example.cs4500_sp19_random1.repositories.FrequentlyAskedAnswerRepository;
import com.example.cs4500_sp19_random1.repositories.FrequentlyAskedQuestionRepository;
import com.example.cs4500_sp19_random1.services.FrequentlyAskedQuestionService;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.*;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FrequentlyAskedQuestionServiceTest {
  @InjectMocks
  private FrequentlyAskedQuestionService frequentlyAskedQuestionService;
  @Mock
  private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;
  @Mock
  private FrequentlyAskedAnswerRepository frequentlyAskedAnswerRepository;


  FrequentlyAskedQuestion frequentlyAskedQuestion1 = new FrequentlyAskedQuestion();
  FrequentlyAskedQuestion faq2 = new FrequentlyAskedQuestion();
  FrequentlyAskedQuestion faqChanged = new FrequentlyAskedQuestion();

  FrequentlyAskedAnswer faqAnswer2 = new FrequentlyAskedAnswer();


  @Before
  public void setUp() {
    frequentlyAskedQuestion1.setTitle("Q1");
    frequentlyAskedQuestion1.setQuestion("What is question one?");


    faq2.setTitle("Q2") ;
    faq2.setQuestion("What is question two?");
    faqAnswer2.setId(2);
    faqAnswer2.setAnswer("Answer to question two.");
    List<FrequentlyAskedAnswer> answers = new ArrayList<>();
    answers.add(faqAnswer2);
    faq2.setAnswers(answers);

    faqChanged.setTitle("Q3");
    faqChanged.setQuestion("Question three");

    List<FrequentlyAskedQuestion> filterResults = new ArrayList<>();
    filterResults.add(frequentlyAskedQuestion1);

    Mockito.when(frequentlyAskedQuestionRepository.findFAQById(1)).
            thenReturn(frequentlyAskedQuestion1);
    Mockito.when(frequentlyAskedQuestionRepository.filterAllFrequentQuestions("Q1",
            "What is question one?")).
            thenReturn(filterResults);
    Mockito.when(frequentlyAskedQuestionRepository.
            filterAllFrequentQuestions(null,null)).thenReturn(new ArrayList<>());
    Mockito.when(frequentlyAskedQuestionRepository.save(faq2)).thenReturn(faq2);
    Mockito.when(frequentlyAskedAnswerRepository.save(faqAnswer2)).thenReturn(faqAnswer2);
    Mockito.when(frequentlyAskedQuestionRepository.findFAQById(2)).thenReturn(faq2);
    Mockito.when(frequentlyAskedAnswerRepository.findAnswerById(2)).thenReturn(faqAnswer2);
  }

  @Test
  public void findFAQbyIdTest() {
    FrequentlyAskedQuestion found = frequentlyAskedQuestionService.findFAQById(1);
    assertEquals("Q1", found.getTitle());
  }

  @Test
  public void exactFilterFAQTest() {
    List<FrequentlyAskedQuestion> foundFAQs = frequentlyAskedQuestionService.filterAllFAQs("Q1",
            "What is question one?");

    assertEquals("Q1", foundFAQs.get(0).getTitle());
    assertEquals("What is question one?", foundFAQs.get(0).getQuestion());
  }

  @Test
  public void emptyFilterFAQTest() {
    List<FrequentlyAskedQuestion> foundFAQs = frequentlyAskedQuestionService.filterAllFAQs("",
            "");

    assertEquals(0, foundFAQs.size());

  }

  @Test
  public void createFAQTest() {
    FrequentlyAskedQuestion frequentlyAskedQuestion1 = new FrequentlyAskedQuestion();
    frequentlyAskedQuestion1.setTitle("Q2");
    frequentlyAskedQuestion1.setQuestion("What is question two?");
    FrequentlyAskedQuestion faqAdded = frequentlyAskedQuestionService.createFAQ(faq2);
    assertEquals("Q2", faqAdded.getTitle());
    assertEquals("What is question two?", faqAdded.getQuestion());
    assertEquals(faqAdded, faq2);
  }

  @Test
  public void updateFAQTest() {
   FrequentlyAskedQuestion updatedFAQ = frequentlyAskedQuestionService.updateFAQ(2, faqChanged);
   assertEquals("Q3" ,updatedFAQ.getTitle());
   assertEquals("Question three" ,updatedFAQ.getQuestion());
  }

  @Test
  public void findAnswerByFAQIdTest() {
    List<FrequentlyAskedAnswer> answers = frequentlyAskedQuestionService.findAnswersByID(2);
    FrequentlyAskedAnswer answer2 = answers.get(0);
    assertEquals(answer2.getAnswer(),faqAnswer2.getAnswer());
  }

  @Test
  public void addFAQToAnswerTest() {
    FrequentlyAskedAnswer answer = frequentlyAskedQuestionService.addAnswer(2, faqAnswer2);
    assertEquals(answer, faqAnswer2);
    assertEquals(answer.getQuestion(),faq2.getQuestion());
  }

  @Test
  public void addAnswerRelationshipTest() {
    List<FrequentlyAskedAnswer> frequentlyAskedAnswers = frequentlyAskedQuestionService.
            addAnswerRelationship(2,2);
    assertEquals(frequentlyAskedAnswers.get(0),faqAnswer2);
    assertEquals(frequentlyAskedAnswers.get(0).getQuestion(), faq2.getQuestion());

  }

  @Test
  public void deleteFAQAnswerRelationshipTest() {
    List<FrequentlyAskedAnswer> frequentlyAskedAnswers = frequentlyAskedQuestionService.
            addAnswerRelationship(2,2);
    assertEquals(frequentlyAskedAnswers.get(0),faqAnswer2);
    assertEquals(frequentlyAskedAnswers.get(0).getQuestion(), faq2.getQuestion());
    frequentlyAskedQuestionService.deleteAnswerRelationship(2,2);
    assertEquals(faqAnswer2.getQuestion(), "");

  }
}
