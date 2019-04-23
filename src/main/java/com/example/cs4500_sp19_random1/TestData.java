package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.repositories.*;
import com.example.cs4500_sp19_random1.util.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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

            createBedroomServiceQuestion(service);
            createBathroomServiceQuestion(service);
            createCleaningServiceQuestion(service);

            createCristinaProvider(cristina, service);
            createShineProvider(shine, service);
            createMendesProvider(mendes, service);

            providers.add(cristina);
            providers.add(shine);
            providers.add(mendes);

            serviceRepository.save(service);
        }
    }

    private void createCleaningServiceQuestion(Service service) {
        ArrayList<ServiceQuestionChoice> choices = new ArrayList<>();
        ServiceQuestionChoice choiceOne = new ServiceQuestionChoice();
        choiceOne.setChoice("Standard Cleaning");
        ServiceQuestionChoice choiceTwo = new ServiceQuestionChoice();
        choiceTwo.setChoice("Deep Cleaning");
        ServiceQuestionChoice choiceThree = new ServiceQuestionChoice();
        choiceThree.setChoice("Move Out");

        createQuestionAndSetChoices(service, choices, choiceOne, choiceTwo, choiceThree, "Cleaning Type");
    }

    private void createBathroomServiceQuestion(Service service) {
        ArrayList<ServiceQuestionChoice> choices = new ArrayList<>();
        ServiceQuestionChoice choiceOne = new ServiceQuestionChoice();
        choiceOne.setChoice("1 bathroom");
        ServiceQuestionChoice choiceTwo = new ServiceQuestionChoice();
        choiceTwo.setChoice("2 bathroom");
        ServiceQuestionChoice choiceThree = new ServiceQuestionChoice();
        choiceThree.setChoice("3 bathroom");

        createQuestionAndSetChoices(service, choices, choiceOne, choiceTwo, choiceThree, "Number of bathrooms");
    }

    private boolean isInDB() {
        List<Service> allServices = serviceRepository.findAllServices();
        for (Service allService : allServices) {
            if (allService.getServiceName().equalsIgnoreCase("House Organizing")) return true;
        }
        return false;
    }

    private void createBedroomServiceQuestion(Service service) {
        ArrayList<ServiceQuestionChoice> choices = new ArrayList<>();
        ServiceQuestionChoice choiceOne = new ServiceQuestionChoice();
        choiceOne.setChoice("1 bedroom");
        ServiceQuestionChoice choiceTwo = new ServiceQuestionChoice();
        choiceTwo.setChoice("2 bedroom");
        ServiceQuestionChoice choiceThree = new ServiceQuestionChoice();
        choiceThree.setChoice("3 bedroom");

        createQuestionAndSetChoices(service, choices, choiceOne, choiceTwo, choiceThree, "Number of bedrooms");

    }

    private void createQuestionAndSetChoices(Service service, ArrayList<ServiceQuestionChoice> choices, ServiceQuestionChoice choiceOne, ServiceQuestionChoice choiceTwo, ServiceQuestionChoice choiceThree, String s) {
        choices.add(choiceOne);
        choices.add(choiceTwo);
        choices.add(choiceThree);

        serviceQuestionChoiceRepository.saveAll(choices);

        ServiceQuestion serviceQuestion = new ServiceQuestion();
        serviceQuestion.setQuestion(s);
        serviceQuestion.setType(QuestionType.MULTIPLE.toString());
        serviceQuestion.setChoices(choices);
        serviceQuestion.setService(service);
        serviceQuestionRepository.save(serviceQuestion);

        choiceOne.setServiceQuestion(serviceQuestion);
        choiceTwo.setServiceQuestion(serviceQuestion);
        choiceThree.setServiceQuestion(serviceQuestion);
        serviceQuestionChoiceRepository.saveAll(choices);

        service.getServiceQuestions().add(serviceQuestion);
    }

    private void createCristinaProvider(ServiceProvider cristina, Service service) {
        createProvider(cristina, service, "Cristina House Cleaning", 4.4f, 10, "$20", "42123");
    }

    private void createMendesProvider(ServiceProvider mendes, Service service) {
        createProvider(mendes, service, "Mendes House Cleaning", 4.1f, 50, "$100", "33222");
    }

    private void createShineProvider(ServiceProvider shine, Service service) {
        createProvider(shine, service, "Shine House Cleaning", 4.1f, 50, "$100", "44555");
    }

    private void createProvider(ServiceProvider provider, Service service, String name, float rating, int hires, String price, String zipCode) {
        Address address = new Address("25 John St.", "Boston", "MA", "12571");
        address.setZipCode(zipCode);
        provider.setBusinessAddress(address);
        provider.setName(name);
        provider.setRating(rating);
        provider.setHires(hires);
        provider.setNumEmployees(10);
        provider.setYearFounded(2010);
        provider.setPrice(price);
        provider.setIntroduction("Lorem ipsum dolor sit amet, consectetur adipiscing.");
        provider.setService(service);
        serviceProviderRepository.save(provider);
    }

}