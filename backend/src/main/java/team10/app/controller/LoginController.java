package team10.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team10.app.security.auth.JWTProvider;
import team10.app.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/login")
public class LoginController {

    private final UserService userService;

    private final JWTProvider jwtProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {

        if (!userService.userExists(username)) {
            throw new Exception("User dos not exist");
        }

        if (!userService.loadUserByEmail(username).get().getPassword().equals(bCryptPasswordEncoder.encode(password))){
            throw new Exception("Invalid credentials!");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        // is called UserDetailsService.LoadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.createToken(authentication);
    }
}
