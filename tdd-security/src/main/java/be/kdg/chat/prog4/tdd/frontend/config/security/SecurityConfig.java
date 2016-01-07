package be.kdg.chat.prog4.tdd.frontend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import be.kdg.chat.prog4.tdd.backend.service.UserService;

/**
 * Configures web security and rest api security aspects.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new Authenticator(this.userService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(request -> {
            final String url = request.getServletPath() + request.getPathInfo();
            return !(url.startsWith("/api/"));
        });

        http.authorizeRequests()
                .antMatchers("/", "/login-error").permitAll()
                .antMatchers("/add-user").hasRole("ADMIN")
                .antMatchers("/add-favorite").hasAnyRole("USER", "ADMIN")
                // remaining URL's require authentication
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error").successHandler(new OnAuthenticationSuccesHandler(this.userService)).permitAll()
                .and()
                .csrf();
    }
}