package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.ServiceCategory;
import com.example.cs4500_sp19_random1.repositories.ServiceCategoryRepository;
import com.example.cs4500_sp19_random1.services.ServiceCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCategoryServiceTest {

    @InjectMocks
    ServiceCategoryService serviceCategoryService;

    @Mock
    ServiceCategoryRepository serviceCategoryRepository;
    private ServiceCategory serviceCategoryOne;
    private ServiceCategory serviceCategoryTwo;
    private ArrayList<ServiceCategory> serviceCategoryList;

    @Before
    public void setup() {
        this.serviceCategoryList = new ArrayList<>();

        this.serviceCategoryOne = new ServiceCategory();
        this.serviceCategoryTwo = new ServiceCategory();

        serviceCategoryOne.setId(123);
        serviceCategoryOne.setTitle("Title");

        serviceCategoryTwo.setId(321);

        serviceCategoryList.add(serviceCategoryOne);
        serviceCategoryList.add(serviceCategoryTwo);

        when(serviceCategoryRepository.findAllServiceCategories()).thenReturn(serviceCategoryList);
        when(serviceCategoryRepository.findServiceCategoryById(123)).thenReturn(serviceCategoryOne);
        when(serviceCategoryRepository.save(serviceCategoryOne)).thenReturn(serviceCategoryOne);
    }

    @Test
    public void testFindAllServiceCategories() {
        List<ServiceCategory> allServiceCategories = serviceCategoryService.findAllServiceCategories();
        assertEquals("Size should be 2", 2, allServiceCategories.size());
    }

    @Test
    public void testFindServiceCategoryById() {
        ServiceCategory serviceCategoryById = serviceCategoryService.findServiceCategoryById(123);
        assertEquals("ID should be 123", new Integer(123), serviceCategoryById.getId());
    }

    @Test
    public void testCreateServiceCategory() {
        ServiceCategory returnedServiceCategory = serviceCategoryService.createServiceCategory(serviceCategoryOne);
        assertEquals("The title should be 'Title'", "Title", returnedServiceCategory.getTitle());
    }

    @Test
    public void testUpdateServiceCategory() {
        ServiceCategory serviceUpdates = new ServiceCategory();
        serviceUpdates.setTitle("Title 2");
        ServiceCategory serviceCategory = serviceCategoryService.updateServiceCategory(123, serviceUpdates);

        assertEquals("The title should be 'Title 2'", "Title 2", serviceCategory.getTitle());
    }

    @Test
    public void testDeleteServiceCategory() {
        serviceCategoryService.deleteServiceCategory(321);
    }

}
