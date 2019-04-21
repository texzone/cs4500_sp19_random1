package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.ServiceQuestion;
import com.example.cs4500_sp19_random1.repositories.ServiceQuestionRepository;
import com.example.cs4500_sp19_random1.services.ServiceQuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class ServiceQuestionsServiceTest {

    @InjectMocks
    private ServiceQuestionService serviceQuestionService;

    @Mock
    ServiceQuestionRepository serviceQuestionRepository;

    ServiceQuestion serviceQuestion = new ServiceQuestion();
    private ServiceQuestion createServiceQuestion;
    private ServiceQuestion updateServiceQuestion;

    @Before
    public void setup() {
        serviceQuestion.setQuestion("Question One?");
        serviceQuestion.setId(789);

        createServiceQuestion = new ServiceQuestion();
        createServiceQuestion.setId(555);
        createServiceQuestion.setQuestion("Create Question?");

        updateServiceQuestion = new ServiceQuestion();
        updateServiceQuestion.setId(789);
        updateServiceQuestion.setQuestion("Update Question?");

        List<ServiceQuestion> serviceQuestions = new ArrayList<>();
        serviceQuestions.add(serviceQuestion);

        Mockito.when(serviceQuestionService.findAll()).thenReturn(serviceQuestions);
        Mockito.when(serviceQuestionService.findById(789)).thenReturn(serviceQuestion);
        Mockito.when(serviceQuestionService.create(createServiceQuestion)).thenReturn(createServiceQuestion);
        Mockito.when(serviceQuestionService.update(789, updateServiceQuestion)).thenReturn(updateServiceQuestion);
        doNothing().when(serviceQuestionRepository).deleteById(789);
    }

    @Test
    public void testFindAll() {
        List<ServiceQuestion> all = serviceQuestionService.findAll();
        assertEquals("Size of findAll should be 1.", 1, all.size());
    }

    @Test
    public void findById() {
        ServiceQuestion returnedServiceQuestion = serviceQuestionService.findById(789);
        assertNotNull(returnedServiceQuestion);
        assertEquals("The question should be 'Question One?'", "Question One?", returnedServiceQuestion.getQuestion());
        assertEquals("The id should be '789'", (long) 789, (long) returnedServiceQuestion.getId());
    }

    @Test
    public void create() {
        ServiceQuestion returnedServiceQuestion = serviceQuestionService.create(createServiceQuestion);
        Integer id = returnedServiceQuestion.getId();
        String createdQuestion = returnedServiceQuestion.getQuestion();
        assertEquals("The created Service Question should be 'Create Question?'",
                "Create Question?",
                createdQuestion);
        assertEquals("The id should be '555'", (long) 555, (long) id);
    }

    @Test
    public void delete() {
        serviceQuestionService.delete(789);
    }

    @Test
    public void update() {
        ServiceQuestion update = serviceQuestionService.update(789, updateServiceQuestion);
        assertEquals("The question should be 'Update Question?", "Update Question?", update.getQuestion());
        assertEquals("The id should be '789'", (long) 789, (long) update.getId());
    }

}
