package com.example.cs4500_sp19_random1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_questions")
public class ServiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String type;

    @OneToMany(mappedBy = "serviceQuestion", orphanRemoval = true)
    private List<ServiceQuestionChoice> choices;
    @OneToMany(mappedBy = "serviceQuestion", orphanRemoval = true)
    @JsonIgnore
    private List<ServiceAnswer> serviceAnswers;

    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JsonIgnore
    private Service service;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ServiceQuestionChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<ServiceQuestionChoice> choices) {
        this.choices = choices;
    }

    public List<ServiceAnswer> getServiceAnswers() {
        return serviceAnswers;
    }

    public void setServiceAnswers(List<ServiceAnswer> serviceAnswers) {
        this.serviceAnswers = serviceAnswers;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}