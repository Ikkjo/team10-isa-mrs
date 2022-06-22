package team10.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team10.app.dto.AdminRegistrationDto;
import team10.app.dto.LoginDto;
import team10.app.util.TestUtil;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerIntegrationTest {

    private static final String URL_PREFIX = "/api/v1/admin";
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private String mainAdminJwt;
    private String adminJwt;
    private String unverifiedAdminJwt;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        // get main admin jwt
        {
            LoginDto dto = new LoginDto();
            dto.setUsername("admin@rentr.com");
            dto.setPassword("admin");
            String json = TestUtil.json(dto);
            mainAdminJwt = mockMvc.perform(post("/api/v1/login")
                    .contentType(contentType).content(json))
                    .andReturn().getResponse().getContentAsString();
        }
        // get admin jwt
        {
            LoginDto dto = new LoginDto();
            dto.setUsername("slavko@rentr.com");
            dto.setPassword("admin");
            String json = TestUtil.json(dto);
            adminJwt = mockMvc.perform(post("/api/v1/login")
                    .contentType(contentType).content(json))
                    .andReturn().getResponse().getContentAsString();
        }
        // get unverified admin jwt
        {
            LoginDto dto = new LoginDto();
            dto.setUsername("damir@rentr.com");
            dto.setPassword("admin");
            String json = TestUtil.json(dto);
            unverifiedAdminJwt = mockMvc.perform(post("/api/v1/login")
                    .contentType(contentType).content(json))
                    .andReturn().getResponse().getContentAsString();
        }
    }

    @Test
    @Transactional
    public void testCreateAdmin() throws Exception {
        AdminRegistrationDto dto = new AdminRegistrationDto();
        dto.setFirstName("Test"); dto.setLastName("Test");
        dto.setEmail("admin.test@test.com");
        dto.setPassword("admin");

        String json = TestUtil.json(dto);
        mockMvc.perform(
                post(URL_PREFIX+"/create-admin")
                        .contentType(contentType).content(json)
                        .header("Authorization", "Bearer "+mainAdminJwt)
        ).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testVerifyAdmin() throws Exception {
        String json = TestUtil.json("newPassword");
        mockMvc.perform(
                put(URL_PREFIX+"/verify-admin")
                        .contentType(contentType).content(json)
                        .header("Authorization", "Bearer "+unverifiedAdminJwt)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Damir"))
        .andExpect(jsonPath("$.lastName").value("Adamovic"))
        .andExpect(jsonPath("$.email").value("damir@rentr.com"))
        .andExpect(jsonPath("$.role").value("ADMIN"))
        .andExpect(jsonPath("$.phoneNumber").value(""));
    }
}
