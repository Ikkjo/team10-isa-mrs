package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.RegistrationRequestDto;
import team10.app.model.User;
import team10.app.security.auth.ConfirmationToken;
import team10.app.util.EmailValidator;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    public String register(RegistrationRequestDto request) {


        if (!emailValidator.test(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("Email address %s, not valid", request.getEmail()));
        }

        if (userService.userExists(request.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("User with email %s already exists", request.getEmail()));
        }

        User user = userService.buildUser(request);

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userService.saveUser(user);

        // TODO : Add exception to throw
        user = userService.getByEmail(user.getEmail(), user.getUserRole()).orElseThrow();


        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        // TODO: Send confirmation email if user is

        return token;
    }

}