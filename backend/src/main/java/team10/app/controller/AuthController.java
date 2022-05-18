package team10.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    private AuthenticationManagerBuilder authenticationManagerBuilder;
//
//
//    @RequestMapping("/login")
//    public String login() {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken("monday"."123456");
//        / / is called UserDetailsService. LoadUserByUsername
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = tokenProvider.createToken(authentication);
//        returntoken; }
}
