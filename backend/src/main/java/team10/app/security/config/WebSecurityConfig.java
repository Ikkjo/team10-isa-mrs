package team10.app.security.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import team10.app.security.auth.JWTAccessDeniedHandler;
import team10.app.security.auth.JWTAuthenticationEntryPoint;
import team10.app.security.auth.JWTProvider;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CorsFilter corsFilter;

    @Autowired
    private final JWTProvider jwtProvider;

    @Autowired
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // Removes the ROLE_ prefix
        return new GrantedAuthorityDefaults("");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // disable CSRF
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                // Authorization is abnormal
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // Prevent iframe from crossing domains
                .and()
                .headers()
                .frameOptions()
                .disable()
                // No session is created
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // Static resources, etc
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html","/**/*.html","/**/*.css","/**/*.js","/webSocket/**"
                ).permitAll()
                // swagger document
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                // file
                .antMatchers("/avatar/**").permitAll()
                .antMatchers("/file/**").permitAll()
                // Alibaba druid
                .antMatchers("/druid/**").permitAll()
                // Allow the OPTIONS request
                .antMatchers(HttpMethod.OPTIONS, "/ * *").permitAll()
                // Interface that does not require authentication
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/registration").permitAll()
                // All requests require authentication
                .anyRequest().authenticated()
                .and().apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(jwtProvider);
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        // https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/cors.html
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:8081", "http://127.0.0.1:8080", "http://127.0.0.1:8081", "0.0.0.0:1025", "http://0.0.0.0:1025"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) {
        // Autentifikacija ce biti ignorisana ispod navedenih putanja (kako bismo ubrzali pristup resursima)
        // Zahtevi koji se mecuju za web.ignoring().antMatchers() nemaju pristup SecurityContext-u

        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/login");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/**");

        // Ovim smo dozvolili pristup statickim resursima aplikacije
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");
    }


}
