package team10.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class RentalEntityControllerTest {

    private static final String BASE_URL = "/api/v1/rental-entity";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get(BASE_URL + "/get/" + "698d9c52-ec17-11ec-8ea0-0242ac120002")).andExpect(status().isOk());
    }

    @Test
    void getAllRentalEntities() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllRentalEntityTitles() {
    }
}