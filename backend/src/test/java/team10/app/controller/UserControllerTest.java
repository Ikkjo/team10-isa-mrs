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
import team10.app.dto.LoginDto;
import team10.app.dto.PasswordChangeDto;
import team10.app.util.TestUtil;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final String URL_PREFIX = "/api/v1/user";
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private String businessClientJwt;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        // get test@gmail.com jwt
        {
            LoginDto dto = new LoginDto();
            dto.setUsername("test@gmail.com");
            dto.setPassword("123456");
            String json = TestUtil.json(dto);
            businessClientJwt = mockMvc.perform(post("/api/v1/login")
                            .contentType(contentType).content(json))
                    .andReturn().getResponse().getContentAsString();
        }
    }

    @Test
    @Transactional
    public void updateEmail() throws Exception {
        mockMvc.perform(put(URL_PREFIX+"/update/email").contentType(contentType)
                        .content("test12@gmail.com")
                        .header("Authorization", "Bearer "+businessClientJwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test12@gmail.com"));
    }

    @Test
    @Transactional
    public void updatePassword() throws Exception {
        PasswordChangeDto dto = new PasswordChangeDto();
        dto.setCurrentPassword("123456");
        dto.setNewPassword("1234567890");

        String json = TestUtil.json(dto);
        mockMvc.perform(put(URL_PREFIX+"/update/password").contentType(contentType)
                        .content(json)
                        .header("Authorization", "Bearer "+businessClientJwt))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updatePassword_throwBadRequest() throws Exception {
        PasswordChangeDto dto = new PasswordChangeDto();
        dto.setCurrentPassword("123456");
        dto.setNewPassword("123456"); // The service will throw exception because new password isn't valid

        String json = TestUtil.json(dto);
        mockMvc.perform(put(URL_PREFIX+"/update/password").contentType(contentType)
                        .content(json)
                        .header("Authorization", "Bearer "+businessClientJwt))
                .andExpect(status().isBadRequest());
    }

}
