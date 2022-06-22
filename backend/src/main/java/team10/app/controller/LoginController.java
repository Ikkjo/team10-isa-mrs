package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.LoginDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/login")
public class LoginController {

    private final UserService userService;
    private final JWTProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping
    public String login(@RequestBody LoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        // is called UserDetailsService.LoadUserByUsername
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.createToken(authentication);
    }
}
