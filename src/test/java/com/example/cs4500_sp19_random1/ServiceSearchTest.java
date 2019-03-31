package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.services.ServiceSearch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ServiceSearchTest {
    @Test
    public void testSearchCriteria() {
        List<SearchPredicate> listPred1 = new ArrayList<>();
        ServiceQuestion searchCriteriaQuestionHowManyRooms = new ServiceQuestion();
        ServiceQuestion searchCriteriaQuestionHavePets = new ServiceQuestion();

        ServiceAnswer criteriaAnswerHavePets = new ServiceAnswer();
        criteriaAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        criteriaAnswerHavePets.setTrueFalseAnswer(true);

        Service serv1 = new Service();
        ServiceSearch servSer1 = new ServiceSearch();

        ServiceAnswer criteriaAnswerHowManyRooms = new ServiceAnswer();
        criteriaAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        criteriaAnswerHowManyRooms.setMaxRangeAnswer(3);
        criteriaAnswerHowManyRooms.setMinRangeAnswer(3);

        searchCriteriaQuestionHowManyRooms.setQuestion("How many rooms?");
        searchCriteriaQuestionHavePets.setQuestion("Have Pets?");

        // ============ ROOMS
        ServiceAnswer bobAnswerHowManyRooms = new ServiceAnswer();
        ServiceAnswer marieAnswerHowManyRooms = new ServiceAnswer();

        bobAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        bobAnswerHowManyRooms.setMaxRangeAnswer(3);
        bobAnswerHowManyRooms.setMinRangeAnswer(3);

        marieAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        marieAnswerHowManyRooms.setMaxRangeAnswer(2);
        marieAnswerHowManyRooms.setMinRangeAnswer(1);
        // ============ ROOMS

        // ============ PETS
        ServiceAnswer bobAnswerHavePets = new ServiceAnswer();
        ServiceAnswer marieAnswerHavePets = new ServiceAnswer();

        bobAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        bobAnswerHavePets.setTrueFalseAnswer(true);

        marieAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        marieAnswerHavePets.setTrueFalseAnswer(false);
        // ============ PETS

        SearchPredicate searchPredicateHowManyRooms = new SearchPredicate(searchCriteriaQuestionHowManyRooms, criteriaAnswerHowManyRooms);
        SearchPredicate searchPredicateHavePets = new SearchPredicate(searchCriteriaQuestionHavePets, criteriaAnswerHavePets);

        listPred1.add(searchPredicateHowManyRooms);
        listPred1.add(searchPredicateHavePets);

        serv1.setServiceName("House cleaning");
        serv1.setProviders(new ArrayList<>());
        List<Service> listServ1 = new ArrayList<>();

        listServ1.add(serv1);

        ServiceProvider providerBob = new ServiceProvider(1, "bob", "abc",
                "bobby", "john", listServ1);

        ServiceProvider providerMarie = new ServiceProvider(1, "marie", "abc",
                "marie", "jane", listServ1);

        //SETTING ANSWERS
        providerBob.setServiceAnswers(new ArrayList<>());
        providerBob.getServiceAnswers().add(bobAnswerHowManyRooms);
        providerBob.getServiceAnswers().add(bobAnswerHavePets);

        providerMarie.setServiceAnswers(new ArrayList<>());
        providerMarie.getServiceAnswers().add(marieAnswerHowManyRooms);
        providerMarie.getServiceAnswers().add(marieAnswerHavePets);

        serv1.getProviders().add(providerBob);
        serv1.getProviders().add(providerMarie);
        SearchCriteria serCri1 = new SearchCriteria(listPred1);
        List<User> foundUsersByCriteria = servSer1.searchForProviders(serv1, serCri1);
        assertEquals("", 2, foundUsersByCriteria.size());

        for (User foundUsersByCriterion : foundUsersByCriteria) {
            System.out.println("User that match the questions & answers criteria: " + foundUsersByCriterion.getFirstName());
        }

    }


    @Test
    public void testSearchResult() {
        List<SearchPredicate> listPred1 = new ArrayList<>();
        ServiceQuestion searchCriteriaQuestionHowManyRooms = new ServiceQuestion();
        ServiceQuestion searchCriteriaQuestionHavePets = new ServiceQuestion();

        ServiceAnswer criteriaAnswerHavePets = new ServiceAnswer();
        criteriaAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        criteriaAnswerHavePets.setTrueFalseAnswer(true);

        Service serv1 = new Service();
        ServiceSearch servSer1 = new ServiceSearch();

        ServiceAnswer criteriaAnswerHowManyRooms = new ServiceAnswer();
        criteriaAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        criteriaAnswerHowManyRooms.setMaxRangeAnswer(3);
        criteriaAnswerHowManyRooms.setMinRangeAnswer(1);

        searchCriteriaQuestionHowManyRooms.setQuestion("How many rooms?");
        searchCriteriaQuestionHavePets.setQuestion("Have Pets?");

        // ============ ROOMS
        ServiceAnswer bobAnswerHowManyRooms = new ServiceAnswer();
        ServiceAnswer marieAnswerHowManyRooms = new ServiceAnswer();

        bobAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        bobAnswerHowManyRooms.setMaxRangeAnswer(3);
        bobAnswerHowManyRooms.setMinRangeAnswer(1);

        marieAnswerHowManyRooms.setServiceQuestion(searchCriteriaQuestionHowManyRooms);
        marieAnswerHowManyRooms.setMaxRangeAnswer(2);
        marieAnswerHowManyRooms.setMinRangeAnswer(1);
        // ============ ROOMS

        // ============ PETS
        ServiceAnswer bobAnswerHavePets = new ServiceAnswer();
        ServiceAnswer marieAnswerHavePets = new ServiceAnswer();

        bobAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        bobAnswerHavePets.setTrueFalseAnswer(true);

        marieAnswerHavePets.setServiceQuestion(searchCriteriaQuestionHavePets);
        marieAnswerHavePets.setTrueFalseAnswer(false);
        // ============ PETS

        SearchPredicate searchPredicateHowManyRooms = new SearchPredicate(searchCriteriaQuestionHowManyRooms, criteriaAnswerHowManyRooms);
        SearchPredicate searchPredicateHavePets = new SearchPredicate(searchCriteriaQuestionHavePets, criteriaAnswerHavePets);

        listPred1.add(searchPredicateHowManyRooms);
        listPred1.add(searchPredicateHavePets);

        serv1.setServiceName("House cleaning");
        serv1.setProviders(new ArrayList<>());
        List<Service> listServ1 = new ArrayList<>();

        listServ1.add(serv1);

        ServiceProvider providerBob = new ServiceProvider(1, "bob", "abc",
                "bobby", "john", listServ1);

        ServiceProvider providerMarie = new ServiceProvider(1, "marie", "abc",
                "marie", "jane", listServ1);

        //SETTING ANSWERS
        providerBob.setServiceAnswers(new ArrayList<>());
        providerBob.getServiceAnswers().add(bobAnswerHowManyRooms);
        providerBob.getServiceAnswers().add(bobAnswerHavePets);

        providerMarie.setServiceAnswers(new ArrayList<>());
        providerMarie.getServiceAnswers().add(marieAnswerHowManyRooms);
        providerMarie.getServiceAnswers().add(marieAnswerHavePets);

        serv1.getProviders().add(providerBob);
        serv1.getProviders().add(providerMarie);
        SearchCriteria serCri1 = new SearchCriteria(listPred1);
        Map<ServiceProvider, Integer> searchResult = servSer1.getSearchResult(serv1, serCri1);
        assertEquals("", 2, searchResult.size());

        searchResult.forEach((serviceProvider, integer) -> {
            System.out.println("User: " + serviceProvider.getFirstName() + ", Scored: " + integer);
        });

    }

}