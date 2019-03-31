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
        ServiceQuestion howManyRoomsQuestion = criteria.getPredicates().get(0).getQuestion();
        ServiceQuestion havePetsQuestion = criteria.getPredicates().get(1).getQuestion();

        List<ServiceProvider> prov1 = new ArrayList<>();
        List<User> users = new ArrayList<>();
        service.getProviders().forEach(serviceProvider -> {
            if (hasTheQuestion(serviceProvider.getServiceAnswers(), howManyRoomsQuestion) && hasTheQuestion(serviceProvider.getServiceAnswers(), havePetsQuestion)) {
                users.add(serviceProvider);
                prov1.add(serviceProvider);
            }
        });

        return users;
    }

    public Map<ServiceProvider, Integer> getSearchResult(Service service, SearchCriteria criteria) {
        ServiceQuestion howManyRoomsQuestion = criteria.getPredicates().get(0).getQuestion();
        ServiceAnswer howManyRoomsAnswer = criteria.getPredicates().get(0).getAnswer();

        ServiceQuestion havePetsQuestion = criteria.getPredicates().get(1).getQuestion();
        ServiceAnswer havePetsAnswer = criteria.getPredicates().get(1).getAnswer();

        List<ServiceProvider> providers = new ArrayList<>();
        service.getProviders().forEach(serviceProvider -> {
            if (hasTheQuestion(serviceProvider.getServiceAnswers(), howManyRoomsQuestion) && hasTheQuestion(serviceProvider.getServiceAnswers(), havePetsQuestion)) {
                providers.add(serviceProvider);
            }
        });

        Map<ServiceProvider, Integer> rangeQuestionFilter = filterProvidersByRangeQuestion(providers, howManyRoomsAnswer, howManyRoomsQuestion);

        Map<ServiceProvider, Integer> havePetsProviderMap = filterProvidersByTrueFalseQuestion(providers, havePetsAnswer, havePetsQuestion);

        Map<ServiceProvider, Integer> result = new HashMap<>();

        for (ServiceProvider provider : providers) {
            Integer rangeQuestionScore = rangeQuestionFilter.get(provider);
            Integer trueFalseQuestionScore = havePetsProviderMap.get(provider);
            if (rangeQuestionScore != null && trueFalseQuestionScore != null) {
                result.put(provider, rangeQuestionScore + trueFalseQuestionScore);
            } else if (rangeQuestionScore == null && trueFalseQuestionScore != null) {
                result.put(provider, trueFalseQuestionScore);
            } else if (rangeQuestionScore != null) {
                result.put(provider, rangeQuestionScore);
            }
        }

        return result ;
    }


    private Map<ServiceProvider, Integer> filterProvidersByTrueFalseQuestion(List<ServiceProvider> providers, ServiceAnswer answer
            , ServiceQuestion question) {
        Map<ServiceProvider, Integer> map = new HashMap<>();
        providers.forEach(provider -> {
            ServiceAnswer serviceAnswer = getAnswerByQuestion(provider.getServiceAnswers(), question);
            if (serviceAnswer.getTrueFalseAnswer() == answer.getTrueFalseAnswer()) {
                map.put(provider, 1);
            }
        });
        return map;
    }

    private Map<ServiceProvider, Integer> filterProvidersByRangeQuestion(List<ServiceProvider> providers, ServiceAnswer answer
            , ServiceQuestion question) {
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

    private boolean hasTheQuestion(List<ServiceAnswer> answers, ServiceQuestion question) {
        for (ServiceAnswer serviceAnswer : answers) {
            if (serviceAnswer.getServiceQuestion().getQuestion().equals(question.getQuestion())) {
                return true;
            }
        }
        return false;
    }
    private ServiceAnswer getAnswerByQuestion(List<ServiceAnswer> answers, ServiceQuestion question) {
        for (ServiceAnswer serviceAnswer : answers) {
            if (serviceAnswer.getServiceQuestion().getQuestion().equals(question.getQuestion())) {
                return serviceAnswer;
            }
        }
        return null;
    }
}