package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.RegistrationRequestDto;
import team10.app.service.RegistrationService;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public String register(@RequestBody RegistrationRequestDto request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
//        return registrationService.confirmToken(token);
        return null;
    }

}
