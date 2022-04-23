package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.RegistrationRequest;
import team10.app.model.User;
import team10.app.util.EmailValidator;

@Service
@AllArgsConstructor
public
class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public String register(RegistrationRequest request) {

        User user = request.getUser();

        if (!emailValidator.test(user.getEmail())) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("Email address %s, not valid", user.getEmail()));
        }

        if (userService.userExists(user)) {
            // TODO: Add custom exception
            throw new IllegalStateException(String.format("User with email %s, and username %s already exists", user.getEmail(), user.getUsername()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        return null;
    }

}