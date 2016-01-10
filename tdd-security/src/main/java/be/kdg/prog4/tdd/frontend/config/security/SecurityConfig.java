package be.kdg.prog4.tdd.frontend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configures web security and rest api security aspects.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(
        basePackages = "be.kdg.prog4.tdd.frontend.config.security",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Authenticator authenticator;
    @Autowired
    private OnAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticator);
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
                .formLogin().loginPage("/login").failureUrl("/login-error").successHandler(this.authenticationSuccessHandler).permitAll()
                .and()
                .csrf();
    }
}