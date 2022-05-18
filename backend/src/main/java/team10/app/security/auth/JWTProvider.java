package team10.app.security.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team10.app.model.User;
import team10.app.service.UserService;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTProvider implements InitializingBean {

    private final UserService userService;

    public static final String AUTHORITIES_KEY = "auth";
    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;


    @Override
    public void afterPropertiesSet() {
        // The token must be encoded in Base64 with a minimum of 88 bits
        String secret = "ThetokenmustbeencodedinBase64withaminimumof88bitstypicallyconfiguredinapplicationymlandneedstobepredefined";
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        jwtBuilder = Jwts.builder().signWith(key, SignatureAlgorithm.HS512);
    }


    public String createToken(Authentication authentication) {
        // Get permission list
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return jwtBuilder
                // Add the ID to ensure that all generated tokens are inconsistent
                .setId(UUID.randomUUID().toString())
                // Permission list
                .claim(AUTHORITIES_KEY, authorities)
                // username
                .setSubject(authentication.getName())
                // Expiration time (one day)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        Object authoritiesStr = claims.get(AUTHORITIES_KEY);
        Collection<SimpleGrantedAuthority> authorities = authoritiesStr != null ?
                Arrays.stream(authoritiesStr.toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()) : Collections.emptyList();
        UserDetails principal = userService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}