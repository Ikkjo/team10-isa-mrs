package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.RegistrationRequest;
import team10.app.service.RegistrationService;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
//        return registrationService.confirmToken(token);
        return null;
    }

}
