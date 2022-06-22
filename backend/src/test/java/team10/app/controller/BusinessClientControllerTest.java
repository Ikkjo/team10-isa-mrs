package team10.app.controller;

import org.junit.Before;
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
import team10.app.dto.LoginDto;
import team10.app.util.TestUtil;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class BusinessClientControllerTest {

    private static final String URL_PREFIX = "/api/v1/business-client";
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private String jwt;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        LoginDto dto = new LoginDto();
        dto.setUsername("test@gmail.com");
        dto.setPassword("123456");
        String json = TestUtil.json(dto);
        jwt = mockMvc.perform(post("/api/v1/login")
                        .contentType(contentType).content(json))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getUserDetails() throws Exception {
        mockMvc.perform(get(URL_PREFIX)
                        .header("Authorization", "Bearer "+jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cd95284b-b6fe-46b4-ade2-491c28e55051"))
                .andExpect(jsonPath("$.firstName").value("Testko"))
                .andExpect(jsonPath("$.lastName").value("Testic"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+381654587514"))
                .andExpect(jsonPath("$.role").value("HOUSE_OWNER"))
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.address.address").value("Bulevar Oslobodjenja 1"))
                .andExpect(jsonPath("$.address.city").value("Novi Sad"))
                .andExpect(jsonPath("$.address.country").value("Serbia"))
                .andExpect(jsonPath("$.dateOfBirth").value("12.06.1991."));
    }

}