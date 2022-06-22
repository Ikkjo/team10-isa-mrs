package team10.app.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.repository.UserRepository;
import team10.app.util.EmailValidator;
import team10.app.util.TestUtil;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRegistrationIntegrationTest {

    private static final String URL_PREFIX = "/api/v1/registration";

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
    public void shouldAddUser() throws Exception {
        ClientRegistrationRequestDto clientRegistrationRequestsDto =
                new ClientRegistrationRequestDto(
                        "Marko",
                        "Markovic",
                        "markomare@mail.com",
                        "markicmare123",
                        "0622623437"
                );
        String registerJson = TestUtil.json(clientRegistrationRequestsDto);
        mockMvc.perform(post(URL_PREFIX + "/client")
                .contentType(contentType).content(registerJson))
                .andExpect(status().isOk());

    }

}
