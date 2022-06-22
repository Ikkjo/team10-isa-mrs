package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.model.Client;
import team10.app.model.User;
import team10.app.security.auth.ConfirmationToken;
import team10.app.util.EmailBuilder;
import team10.app.util.EmailValidator;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailSender;

    public String register(ClientRegistrationRequestDto request) {
        if (!emailValidator.test(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("Email address %s, not valid", request.getEmail()));
        }

        if (userService.userExists(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("User with email %s already exists", request.getEmail()));
        }

        Client user = userService.buildClient(request);

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userService.saveClient(user);
        user.setPhoneNumber(request.getPhoneNumber());
        user = userService.getClientByEmail(user.getEmail()).orElseThrow();

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);


        String link = "http://localhost:8888/api/v1/registration/confirm?token=" + token;
        emailSender.send(
                request.getEmail(),
                EmailBuilder.getConfirmRegistrationEmail(request.getFirstName(), link),
                "Confirm your registration");
        return token;
    }

}
