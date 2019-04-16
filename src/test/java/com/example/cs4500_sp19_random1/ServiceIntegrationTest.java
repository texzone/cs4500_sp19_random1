package com.example.cs4500_sp19_random1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.repositories.ServiceRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ServiceIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rt;
    @Autowired
    private ServiceRepository sr;
    private Service testService = null;
    @Before
    public void setup() {
        port = 8080;
        testService = new Service();
        testService.setId(123);
        testService.setServiceName("Pup Walkin");
        testService = sr.save(testService);
    }
    @After
    public void teardown() {
        sr.deleteById(testService.getId());
    }

    @Test
    public void testUpdateService() throws Exception {
        Service svc1 = new Service();
        svc1.setServiceName("Service One");

        Service svc2 = sr.save(svc1);
        svc1.setServiceName("Service Two");

        this.rt.put(
                "http://localhost:8080/api/services/" + svc2.getId(),
                svc1);

        svc2 = sr.findServiceById(svc2.getId());
        assertEquals(svc2.getServiceName(), svc1.getServiceName());

        sr.deleteById(svc2.getId());
    }

    @Test
    public void testDeleteService() throws Exception {
        Service svc1 = new Service();
        svc1.setServiceName("Service One");
        Service savedSvc = sr.save(svc1);

        this.rt.delete("http://localhost:8080/api/services/" + savedSvc.getId());
        Service deletedSvc = sr.findServiceById(savedSvc.getId());
        assertEquals(deletedSvc, null);
    }


    @Test
    public void testCreateService() throws Exception {
        Service svc1 = new Service();
        svc1.setServiceName("Service One");

        Service createdSvc = (Service) rt.postForObject(
                "http://localhost:8080/api/services/",
                svc1,
                Service.class);

        assertEquals(svc1.getServiceName(), createdSvc.getServiceName());

        sr.deleteById(createdSvc.getId());
    }
}
