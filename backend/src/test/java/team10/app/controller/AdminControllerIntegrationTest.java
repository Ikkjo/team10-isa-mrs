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

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @Transactional
    public void testCreateAdmin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin@rentr.com");
        loginDto.setPassword("admin");
        String loginJson = TestUtil.json(loginDto);
        String mainAdminJwt = mockMvc.perform(post("/api/v1/login")
                .contentType(contentType).content(loginJson))
                .andReturn().getResponse().getContentAsString();

        AdminRegistrationDto dto = new AdminRegistrationDto();
        dto.setFirstName("Test"); dto.setLastName("Test");
        dto.setEmail("admin.test@test.com");
        dto.setPassword("admin");
        String json = TestUtil.json(dto);

        mockMvc.perform(
                post(URL_PREFIX+"/create-admin")
                        .contentType(contentType).content(json)
                        .header("Authorization", "Bearer "+mainAdminJwt)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Test"))
        .andExpect(jsonPath("$.lastName").value("Test"))
        .andExpect(jsonPath("$.email").value("admin.test@test.com"))
        .andExpect(jsonPath("$.role").value("UNVERIFIED_ADMIN"))
        .andExpect(jsonPath("$.phoneNumber").value(""));
    }

    @Test
    @Transactional
    public void testVerifyAdmin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("damir@rentr.com");
        loginDto.setPassword("admin");
        String loginJson = TestUtil.json(loginDto);
        String unverifiedAdminJwt = mockMvc.perform(post("/api/v1/login")
                .contentType(contentType).content(loginJson))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(
                put(URL_PREFIX+"/verify-admin")
                        .contentType(contentType).content("newPassword")
                        .header("Authorization", "Bearer "+unverifiedAdminJwt)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Damir"))
        .andExpect(jsonPath("$.lastName").value("Adamovic"))
        .andExpect(jsonPath("$.email").value("damir@rentr.com"))
        .andExpect(jsonPath("$.role").value("ADMIN"))
        .andExpect(jsonPath("$.phoneNumber").value(""));
    }

    @Test
    @Transactional
    public void testRegistrationRequestAccept() throws Exception {
        LoginDto dto = new LoginDto();
        dto.setUsername("slavko@rentr.com");
        dto.setPassword("admin");
        String json = TestUtil.json(dto);
        String adminJwt = mockMvc.perform(post("/api/v1/login")
                .contentType(contentType).content(json))
                .andReturn().getResponse().getContentAsString();

        String registrationRequest = "611619a9-394f-42b3-aad7-7fb10bf03c95";
        mockMvc.perform(
                put(URL_PREFIX+"/registration-request/"+registrationRequest+"/accept")
                        .header("Authorization", "Bearer "+adminJwt)
        ).andExpect(status().isOk());
    }
}
