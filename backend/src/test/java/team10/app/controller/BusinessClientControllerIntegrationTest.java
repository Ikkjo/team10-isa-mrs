package team10.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import team10.app.dto.AddressDto;
import team10.app.security.auth.AuthUtil;
import team10.app.security.auth.JWTAccessDeniedHandler;
import team10.app.security.auth.JWTAuthenticationEntryPoint;
import team10.app.security.auth.JWTProvider;
import team10.app.security.config.WebSecurityConfig;
import team10.app.service.BusinessClientService;
import team10.app.service.RatingService;
import team10.app.service.ReservationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import javax.servlet.ServletContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class BusinessClientControllerIntegrationTest {

    private static final String BASE_URL = "/api/v1/business-client";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private BusinessClientService businessClientService;
    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RatingService ratingService;
    @MockBean
    private AuthUtil authUtil;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void updateAddress() throws Exception {
        String url = BASE_URL + "/update/address";
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("Address");
        addressDto.setCity("City");
        addressDto.setCountry("Country");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(addressDto);
        mockMvc.perform(put(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateDateOfBirth() {
    }

    @Test
    void getEarningsReport() {
    }

    @Test
    void testGetEarningsReport() {
    }

    @Test
    void getRatingReport() {

    }
}