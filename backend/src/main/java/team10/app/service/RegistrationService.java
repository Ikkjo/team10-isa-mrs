package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.BusinessClientRegistrationRequestDto;
import team10.app.model.BusinessClient;
import team10.app.model.RegistrationRequest;
import team10.app.repository.RegistrationRequestRepository;
import team10.app.util.EmailValidator;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationRequestRepository registrationRequestRepository;


    public void register(BusinessClientRegistrationRequestDto request) {
        if (!emailValidator.test(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("Email address %s, not valid", request.getEmail()));
        }

        if (userService.userExists(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("User with email %s already exists", request.getEmail()));
        }

        BusinessClient user = userService.buildBusinessUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.saveBusinessUser(user);

        user = userService.getBusinessPartnerByEmail(user.getEmail(), user.getRole())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        registrationRequestRepository.save(new RegistrationRequest(user, request.getRegistrationReason()));
    }

}