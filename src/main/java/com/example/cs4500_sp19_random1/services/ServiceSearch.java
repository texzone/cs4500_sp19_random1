package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceSearch {
    public List<User> searchForProviders(Service service, SearchCriteria criteria) {
        // Get the list of users which have the selected service answer
        // max, and true and false
        ServiceQuestion quest1 = criteria.getPredicates().get(0).getQuestion();
        ServiceAnswer answ1 = criteria.getPredicates().get(0).getAnswer();
        List<ServiceProvider> prov1 = new ArrayList<>();
        List<User> users = new ArrayList<>();
        service.getProviders().forEach(serviceProvider -> {
            if (hasTheQuestion(serviceProvider.getServiceAnswers(), quest1)) {
                users.add(serviceProvider);
                prov1.add(serviceProvider);
            }
        });
        return users;
    }


    public Map<ServiceProvider, Integer> getSearchResult(Service service, SearchCriteria criteria) {
        ServiceQuestion quest1 = criteria.getPredicates().get(0).getQuestion();
        ServiceAnswer answ1 = criteria.getPredicates().get(0).getAnswer();
        List<ServiceProvider> prov1 = new ArrayList<>();
        service.getProviders().forEach(serviceProvider -> {
            if (hasTheQuestion(serviceProvider.getServiceAnswers(), quest1)) {
                prov1.add(serviceProvider);
            }
        });
        return filterServiceProviders(prov1, answ1, quest1);
    }


    public Map<ServiceProvider, Integer> filterServiceProviders(List<ServiceProvider> providers, ServiceAnswer answer
    ,ServiceQuestion question) {
        Map<ServiceProvider, Integer> map = new HashMap<>();
        providers.forEach(provider -> {
            ServiceAnswer serv1 = getAnswerByQuestion(provider.getServiceAnswers(), question);
            if (serv1.getMaxRangeAnswer() <= answer.getMaxRangeAnswer() &&
            serv1.getMinRangeAnswer() >= answer.getMinRangeAnswer()) {
                map.put(provider, 1);
            }
        });
        return map;
    }

    public boolean hasTheQuestion(List<ServiceAnswer> answers, ServiceQuestion question) {
        for (ServiceAnswer serviceAnswer : answers) {
            if (serviceAnswer.getServiceQuestion().getQuestion()
                    == question.getQuestion()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public ServiceAnswer getAnswerByQuestion(List<ServiceAnswer> answers, ServiceQuestion question) {
        for (ServiceAnswer serviceAnswer : answers) {
            if (serviceAnswer.getServiceQuestion().getQuestion() == question.getQuestion()) {
                return serviceAnswer;
            } else {
                return null;
            }
        }
        return null;
    }
}
