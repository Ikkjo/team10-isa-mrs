package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.BusinessClientRegistrationRequestDto;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.repository.UserRepository;
import team10.app.security.auth.ConfirmationToken;
import team10.app.service.ClientRegistrationService;
import team10.app.service.ConfirmationTokenService;
import team10.app.service.RegistrationService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository userRepository;
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

    @Transactional
    @GetMapping(path = "/confirm")
    public String confirmRegistration(@RequestParam String token) {
        ConfirmationToken t = confirmationTokenService.getToken(token).get();
        if (!LocalDateTime.now().isAfter(t.getExpiresAt())) {
            confirmationTokenService.setConfirmedAt(token);
            userRepository.enableUser(confirmationTokenService.getToken(token).get().getUser().getEmail());
            return "Activation successful!";
        }
        return "Activation unsuccessful, token expired!";
    }

}
