package com.example.cs4500_sp19_random1.services;

import com.example.cs4500_sp19_random1.models.ServiceQuestion;
import com.example.cs4500_sp19_random1.repositories.ServiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class ServiceQuestionService {

    @Autowired
    private ServiceQuestionRepository serviceQuestionRepository;

    @PostMapping("/service-questions")
    public ServiceQuestion create(@RequestBody ServiceQuestion serviceQuestion) {
        return serviceQuestionRepository.save(serviceQuestion);
    }

    @GetMapping("/service-questions")
    public List<ServiceQuestion> findAll() {
        return (List<ServiceQuestion>) serviceQuestionRepository.findAll();
    }

    @GetMapping("/service-questions/{id}")
    public ServiceQuestion findById(@PathVariable Integer id) {
        Optional<ServiceQuestion> byId = serviceQuestionRepository.findById(id);
        return byId.orElse(null);
    }

    @PutMapping("/service-questions/{id}")
    public ServiceQuestion update(@PathVariable Integer id, @RequestBody ServiceQuestion serviceQuestion) {
        serviceQuestion.setId(id);
        return serviceQuestionRepository.save(serviceQuestion);
    }

    @DeleteMapping("/service-questions/{id}")
    public void delete(@PathVariable Integer id) {
        serviceQuestionRepository.deleteById(id);
    }

}
