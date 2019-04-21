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

import com.example.cs4500_sp19_random1.services.ServiceService;
import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.dtos.ServiceDTO;
import com.example.cs4500_sp19_random1.repositories.ServiceRepository;
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
@WebMvcTest(ServiceService.class)
public class ServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceService serviceService;
    @MockBean
    private ServiceRepository serviceRepository;

    @Test
    public void testFindServiceById() throws Exception {
        ServiceDTO dogWalking = new ServiceDTO();
        dogWalking.setId(345);
        dogWalking.setServiceName("Dog Walking");

        when(serviceService.findServiceById(345)).thenReturn(dogWalking);
        this.mockMvc
            .perform(get("/api/services/345"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(345)))
            .andExpect(jsonPath("$.serviceName", is("Dog Walking")));
    }

    @Test
    public void testFindAllServices() throws Exception {
        ServiceDTO dogWalking = new ServiceDTO();
        dogWalking.setId(345);
        dogWalking.setServiceName("Dog Walking");

        ServiceDTO physicalTraining = new ServiceDTO();
        physicalTraining.setId(678);
        physicalTraining.setServiceName("Physical Training");

        List<ServiceDTO> services = Arrays.asList(dogWalking, physicalTraining);
        when(serviceService.findAllService()).thenReturn(services);
        this.mockMvc
            .perform(get("/api/services/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[*].serviceName", 
                        containsInAnyOrder("Dog Walking","Physical Training")));;
    }
}
