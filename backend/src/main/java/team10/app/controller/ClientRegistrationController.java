package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.service.ClientRegistrationService;

@RestController
@RequestMapping(path = "api/v1/client/registration")
@AllArgsConstructor
public class ClientRegistrationController {

    private ClientRegistrationService clientRegistrationService;

    @PostMapping
    public String register(@RequestBody ClientRegistrationRequestDto request) {
        return clientRegistrationService.register(request);
    }

}
