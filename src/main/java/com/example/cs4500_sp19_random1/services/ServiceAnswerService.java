package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.models.ServiceAnswer;
import com.example.cs4500_sp19_random1.repositories.ServiceAnswerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceAnswerService {

    @Autowired
    ServiceAnswerServiceRepository repo;


    @GetMapping("api/admin/service-answers")
    public List<ServiceAnswer> findAllServiceAnswers() {
        return repo.findAllServiceAnswers();
    }
    @GetMapping("api/admin/service-answers/{qid}")
    public ServiceAnswer findAllServiceAnswersById(@PathVariable Integer qid) {
        return repo.findServiceAnswerById(qid);
    }
}
