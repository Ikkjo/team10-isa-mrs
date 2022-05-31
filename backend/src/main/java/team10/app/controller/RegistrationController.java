package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.BusinessClientRegistrationRequestDto;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.service.ClientRegistrationService;
import team10.app.service.RegistrationService;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private ClientRegistrationService clientRegistrationService;


    @PostMapping("/business-client")
    public ResponseEntity<HttpStatus> registerBusinessClient(@RequestBody BusinessClientRegistrationRequestDto request) {
        try {
            registrationService.register(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/client")
    public String registerClient(@RequestBody ClientRegistrationRequestDto request) {
        return clientRegistrationService.register(request);
    }

}
