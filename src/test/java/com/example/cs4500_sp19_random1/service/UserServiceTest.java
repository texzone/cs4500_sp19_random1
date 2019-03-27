package com.example.cs4500_sp19_random1.service;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.repositories.*;
import com.example.cs4500_sp19_random1.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
@AutoConfigureMockMvc
public class UserServiceTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserRepository userRepository;
  @MockBean
  private ServiceRepository serviceRepository;

  User ali = new User(0, "ali", "lol", "ali", "gabri");
  User buh = new User(1, "buh", "quick", "buh", "quick");


  @Test
  public void testDeleteUser() throws Exception {
    doNothing().when(userRepository).deleteById(0);
    this.mockMvc
            .perform(delete("/api/users/-")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  public void testFindAllUsers() throws Exception {
    when(userRepository.findAllUsers()).thenReturn(Arrays.asList(ali, buh));
    this.mockMvc
            .perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[*].username", containsInAnyOrder("ali", "buh")));
  }

  @Test
  public void testCreateUser() throws Exception {
    ObjectMapper aliMapper = new ObjectMapper();


    when(userRepository.save(ali)).thenReturn(buh);
    this.mockMvc
            .perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(aliMapper.writeValueAsString(ali)))
            .andExpect(status().isOk())
            .andReturn();
  }
  

  @Test
  public void testFindUserById() throws Exception {
    when(userRepository.findUserById(0)).thenReturn(ali);
    this.mockMvc
            .perform(get("/api/users/0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(0)))
            .andExpect(jsonPath("$.username", is("ali")))
            .andExpect(jsonPath("$.password", is("lol")))
            .andExpect(jsonPath("$.firstName", is("ali")))
            .andExpect(jsonPath("$.lastName", is("gabri")));
  }

  @Test
  public void testUpdateUser() throws Exception {
    ObjectMapper aliMapper = new ObjectMapper();

    User ali2 = new User(3, "ali2", "lol2", "ali2", "gabri2");

    when(userRepository.save(ali)).thenReturn(ali2);
    when(userRepository.findUserById(123)).thenReturn(ali2);
    this.mockMvc
            .perform(put("/api/users/123")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(aliMapper.writeValueAsString(ali)))
            .andExpect(status().isOk());
  }
}

