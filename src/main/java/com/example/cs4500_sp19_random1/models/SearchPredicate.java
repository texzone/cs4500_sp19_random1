package com.example.cs4500_sp19_random1.models;

public class SearchPredicate {
    private ServiceQuestion question;
    private ServiceAnswer answer;

    public SearchPredicate(ServiceQuestion question, ServiceAnswer answer) {
        this.question = question;
        this.answer = answer;
    }

    public ServiceQuestion getQuestion() {
        return this.question;
    }
    public ServiceAnswer getAnswer() {
        return this.answer;
    }
    public void setQuestion(ServiceQuestion question) {
        this.question = question;
    }
    public void setAnswer(ServiceAnswer answer) {
        this.answer = answer;
    }
}
