package com.example.cs4500_sp19_random1;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import com.example.cs4500_sp19_random1.services.UserService;
import com.example.cs4500_sp19_random1.models.*;
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
@WebMvcTest(UserService.class)
public class UserTest {
  @Autowired
  MockMvc mvc;
  @MockBean
  UserService us;

  User ali = new User(0, "ali", "lol", "ali", "gabri");
  User buh = new User(1, "buh", "quick", "buh", "quick");

  @Test
  public void testFindUserById() throws Exception {
    when(us.findUserById(0)).thenReturn(ali);
    this.mvc
            .perform(get("/api/users/0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(0)))
            .andExpect(jsonPath("$.username", is("ali")))
            .andExpect(jsonPath("$.password", is("lol")))
            .andExpect(jsonPath("$.firstName", is("ali")))
            .andExpect(jsonPath("$.lastName", is("gabri")));
  }

  @Test
  public void testFindAllUsers() throws Exception {
    when(us.findAllUser()).thenReturn(Arrays.asList(ali, buh));
    this.mvc
            .perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[*].username", containsInAnyOrder("ali", "buh")));
  }

  @Test
  public void testCreateUser() throws Exception {
    ObjectMapper aliMapper = new ObjectMapper();


    when(us.createUser(ali)).thenReturn(buh);
    MvcResult result = this.mvc
            .perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(aliMapper.writeValueAsString(ali)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void testUpdateUser() throws Exception {
    ObjectMapper aliMapper = new ObjectMapper();

    User ali2 = new User(3, "ali2", "lol2", "ali2", "gabri");

    when(us.updateUser(ali.getId(), ali2)).thenReturn(ali);
    this.mvc
            .perform(put("/api/users/3")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(aliMapper.writeValueAsString(ali2)))
            .andExpect(status().isOk());
  }
}
