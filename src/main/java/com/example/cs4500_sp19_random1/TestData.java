package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TestData {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    ServiceQuestionRepository serviceQuestionRepository;

    @Autowired
    ServiceAnswerServiceRepository serviceAnswerServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceQuestionChoiceRepository serviceQuestionChoiceRepository;


    @PostConstruct
    public void setup() {
        insertData();
    }

    private void insertData() {
        if (!isInDB()) {
            ArrayList<ServiceProvider> providers = new ArrayList<>();
            Service service = new Service();

            service.setServiceName("House Organizing");
            service.setServiceQuestions(new ArrayList<>());

            service.setProviders(providers);
            service = serviceRepository.save(service);

            ServiceProvider cristina = new ServiceProvider();
            ServiceProvider shine = new ServiceProvider();
            ServiceProvider mendes = new ServiceProvider();

            createServiceQuestions(service);
            createServiceQuestions(service);
            createServiceQuestions(service);
            createProvider(cristina, service);
            createProvider(shine, service);
            createProvider(mendes, service);
            providers.add(cristina);
            providers.add(shine);
            providers.add(mendes);
            serviceRepository.save(service);
        }
    }

    private boolean isInDB() {
        List<Service> allServices = serviceRepository.findAllServices();
        for (Service allService : allServices) {
            if (allService.getServiceName().equalsIgnoreCase("House Organizing")) return true;
        }
        return false;
    }

    private void createServiceQuestions(Service service) {
        ArrayList<ServiceQuestionChoice> choices = new ArrayList<>();
        ServiceQuestionChoice choiceOne = new ServiceQuestionChoice();
        choiceOne.setChoice("1 bedroom");
        ServiceQuestionChoice choiceTwo = new ServiceQuestionChoice();
        choiceTwo.setChoice("2 bedroom");
        ServiceQuestionChoice choiceThree = new ServiceQuestionChoice();
        choiceThree.setChoice("3 bedroom");

        choices.add(choiceOne);
        choices.add(choiceTwo);
        choices.add(choiceThree);

        serviceQuestionChoiceRepository.saveAll(choices);

        ServiceQuestion serviceQuestion1 = new ServiceQuestion();
        serviceQuestion1.setQuestion("Number of bedrooms");
        serviceQuestion1.setType(QuestionType.MULTIPLE.toString());
        serviceQuestion1.setChoices(choices);
        serviceQuestion1.setService(service);
        ServiceQuestion savedQuestion = serviceQuestionRepository.save(serviceQuestion1);

        choiceOne.setServiceQuestion(serviceQuestion1);
        choiceTwo.setServiceQuestion(serviceQuestion1);
        choiceThree.setServiceQuestion(serviceQuestion1);
        serviceQuestionChoiceRepository.saveAll(choices);

        service.getServiceQuestions().add(serviceQuestion1);
    }

    private void createProvider(ServiceProvider cristina, Service service) {
        cristina.setName("Cristina House Cleaning");
        cristina.setRating(4.4f);
        cristina.setYearsInBusiness(7);
        cristina.setHires(10);
        cristina.setPrice("$20");
        cristina.setEmployees(2);
        cristina.setIntroduction("Lorem ipsum dolor sit amet, consectetur adipiscing.");
        cristina.setService(service);
        serviceProviderRepository.save(cristina);
    }

}