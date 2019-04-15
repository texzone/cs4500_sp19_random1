package com.example.cs4500_sp19_random1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
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
import com.example.cs4500_sp19_random1.repositories.ServiceCategoryRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ServiceCategoryIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rt;
    @Autowired
    private ServiceCategoryRepository scr;
    private ServiceCategory testCat = null;
    @Before
    public void setup() {
        port = 8080;
        testCat = new ServiceCategory();
        testCat.setId(123);
        testCat.setTitle("Pet Services");
        testCat = scr.save(testCat);
    }
    @After
    public void teardown() {
        scr.deleteById(testCat.getId());
    }

    @Test
    public void testUpdateServiceCategory() throws Exception {
        ServiceCategory svcCat = new ServiceCategory();
        svcCat.setTitle("Service Category One");

        ServiceCategory svcCat2 = scr.save(svcCat);
        svcCat.setTitle("Service Category Two");

        

        this.rt.put(
                "http://localhost:8080/api/categories/" + svcCat2.getId(),
                svcCat);

        svcCat = scr.findServiceCategoryById(svcCat2.getId());
        assertEquals(svcCat2.getTitle(), svcCat.getTitle());

        scr.deleteById(svcCat2.getId());
    }

    @Test
    public void testDeleteServiceCategory() throws Exception {
        ServiceCategory svcCat = new ServiceCategory();
        svcCat.setTitle("Service Category One");
        ServiceCategory savedSvcCat = scr.save(svcCat);

        this.rt.delete("http://localhost:8080/api/categories/" + savedSvcCat.getId());
        ServiceCategory deletedSvcCat = scr.findServiceCategoryById(savedSvcCat.getId());
        assertEquals(deletedSvcCat, null);
    }


    @Test
    public void testCreateServiceCategory() throws Exception {
        ServiceCategory svcCat = new ServiceCategory();
        svcCat.setTitle("Service Category One");

        ServiceCategory createdSvcCat = (ServiceCategory) rt.postForObject(
                "http://localhost:8080/api/categories/",
                svcCat,
                ServiceCategory.class);

        assertEquals(svcCat.getTitle(), createdSvcCat.getTitle());

        scr.deleteById(createdSvcCat.getId());
    }
}
