package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.services.ServiceSearch;
import org.junit.Test;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ServiceSearchTest {
    @Test
    public void testSearchCriteria() {
        ServiceQuestion ques1 = new ServiceQuestion();
        ques1.setQuestion("How many rooms?");
        ServiceAnswer answ1 = new ServiceAnswer();
        answ1.setServiceQuestion(ques1);
        answ1.setMaxRangeAnswer(3);
        answ1.setMinRangeAnswer(3);
        SearchPredicate serPred1 = new SearchPredicate(ques1, answ1);
        List<SearchPredicate> listPred1 = new ArrayList<>();
        listPred1.add(serPred1);
        Service serv1 = new Service();
        serv1.setServiceName("House cleaning");
        serv1.setProviders(new ArrayList<>());
        List<Service> listServ1 = new ArrayList<>();
        listServ1.add(serv1);
        ServiceProvider prov1 = new ServiceProvider(1,"bob", "abc",
                "bobby", "john", listServ1);
       //SETTING ANSWERS
        prov1.setServiceAnswers(new ArrayList<>());
        prov1.getServiceAnswers().add(answ1);

        serv1.getProviders().add(prov1);
        SearchCriteria serCri1 = new SearchCriteria(listPred1);
        ServiceSearch servSer1 = new ServiceSearch();
        assertEquals("",1, servSer1.searchForProviders(serv1, serCri1).size());
    }
}
