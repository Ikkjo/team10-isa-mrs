package team10.app.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import team10.app.security.filter.UserAuthenticationFilter;
import team10.app.security.filter.UserAuthorizationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(authenticationManagerBean());
        userAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

//        http
//
//                // Disable CSRF
//                .csrf().disable()
//                // Disabling session authentication
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // Enable public api calls
//                .authorizeRequests()
//                .antMatchers("/api/v1/**")
//                .permitAll()
//                .antMatchers("api/v1/login")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .cors().and();
//                .addFilter(userAuthenticationFilter)
//                .addFilterBefore(new UserAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // default /login is public
        // Authorization for login and register not needed because they are public
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .antMatchers("/api/token/refresh").permitAll()
                .antMatchers("/api/v1/registration").permitAll()
                .antMatchers("api/v1/client/registration").permitAll();
        // Authorization for user specific api calls
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/client/**").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/vacation-home-owner/**").hasAnyAuthority("HOUSE_OWNER")
                .antMatchers(HttpMethod.POST, "/api/v1/fishing-instructor/**").hasAnyAuthority("FISHING_INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/api/v1/ship-owner/**").hasAnyAuthority("SHIP_OWNER")
                .anyRequest()
                .authenticated()
                .and().cors().and();

        //filter
        http.addFilter(userAuthenticationFilter);
        http.addFilterBefore(new UserAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
