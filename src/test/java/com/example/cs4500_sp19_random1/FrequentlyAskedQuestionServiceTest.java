package com.example.cs4500_sp19_random1;

import java.util.ArrayList;
import java.util.List;

import com.example.cs4500_sp19_random1.models.FrequentlyAskedQuestion;
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

  @Before
  public void setUp() {
    FrequentlyAskedQuestion frequentlyAskedQuestion1 = new FrequentlyAskedQuestion();
    frequentlyAskedQuestion1.setTitle("Q1");
    frequentlyAskedQuestion1.setQuestion("What is question one?");

    List<FrequentlyAskedQuestion> filterResults = new ArrayList<>();
    filterResults.add(frequentlyAskedQuestion1);

    Mockito.when(frequentlyAskedQuestionRepository.findFAQById(1)).
            thenReturn(frequentlyAskedQuestion1);
    Mockito.when(frequentlyAskedQuestionRepository.filterAllFrequentQuestions("Q1",
            "What is question one?")).
            thenReturn(filterResults);
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
}
