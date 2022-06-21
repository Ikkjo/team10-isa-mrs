package team10.app.security.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUtil {
    private final JWTProvider jwtProvider;

    public String getEmailFromToken(String token) {
        return jwtProvider.getAuthentication(token.substring(7)).getName();
    }
}
