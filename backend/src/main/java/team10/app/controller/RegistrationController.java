package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.BusinessClientRegistrationRequestDto;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.service.ClientRegistrationService;
import team10.app.service.RegistrationService;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class BusinessClientRegistrationController {

    private final RegistrationService registrationService;
    private ClientRegistrationService clientRegistrationService;


    @PostMapping("/business-client")
    public String registerBusinessClient(@RequestBody BusinessClientRegistrationRequestDto request) {
        return registrationService.register(request);
    }

    @PostMapping(path = "/client")
    public String registerClient(@RequestBody ClientRegistrationRequestDto request) {
        return clientRegistrationService.register(request);
    }

}
