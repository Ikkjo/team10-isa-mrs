package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.ClientRegistrationRequest;

@AllArgsConstructor
@Service
public class ClientRegistrationService {

    public String register(ClientRegistrationRequest request) {
        return "works!";
    }
}
