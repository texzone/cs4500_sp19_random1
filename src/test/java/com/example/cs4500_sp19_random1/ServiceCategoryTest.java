package com.example.cs4500_sp19_random1;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.example.cs4500_sp19_random1.services.ServiceCategoryService;
import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.repositories.ServiceCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@WebMvcTest(ServiceCategoryService.class)
public class ServiceCategoryTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceCategoryService serviceCatService;
    @MockBean
    private ServiceCategoryRepository serviceCatRepository;

    @Test
    public void testFindServiceCategoryById() throws Exception {
        Service dogWalking = new Service();
        dogWalking.setId(345);
        dogWalking.setServiceName("Dog Walking");

        Service catGroom = new Service();
        catGroom.setId(456);
        catGroom.setServiceName("Cat Grooming");
        List<Service> svcs = Arrays.asList(dogWalking, catGroom);

        ServiceCategory pets = new ServiceCategory();
        pets.setId(113);
        pets.setTitle("Pet Services");
        pets.setServices(svcs);


        when(serviceCatService.findServiceCategoryById(113)).thenReturn(pets);
        this.mockMvc
            .perform(get("/api/categories/113"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(113)))
            .andExpect(jsonPath("$.title", is("Pet Services")))
            .andExpect(jsonPath("$.services[*]", hasSize(2)))
            .andExpect(jsonPath("$.services[*].serviceName",
                        containsInAnyOrder("Dog Walking", "Cat Grooming")));
    }

    @Test
    public void testFindAllServices() throws Exception {
        Service dogWalking = new Service();
        dogWalking.setId(345);
        dogWalking.setServiceName("Dog Walking");

        Service catGroom = new Service();
        catGroom.setId(456);
        catGroom.setServiceName("Cat Grooming");

        Service physicalTraining = new Service();
        physicalTraining.setId(678);
        physicalTraining.setServiceName("Physical Training");
        
        List<Service> services = Arrays.asList(dogWalking, catGroom);

        ServiceCategory pets = new ServiceCategory();
        pets.setId(113);
        pets.setTitle("Pet Services");
        pets.setServices(services);

        ServiceCategory health = new ServiceCategory();
        health.setId(213);
        health.setTitle("Health Services");

        List<ServiceCategory> serviceCats = Arrays.asList(pets, health);

        when(serviceCatService.findAllServiceCategories()).thenReturn(serviceCats);
        this.mockMvc
            .perform(get("/api/categories/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[*].title", 
                        containsInAnyOrder("Pet Services","Health Services")));;
    }
}

