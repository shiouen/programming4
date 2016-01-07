package be.kdg.chat.prog4.tdd.frontend.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.kdg.chat.prog4.tdd.backend.model.User;
import be.kdg.chat.prog4.tdd.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements AuthenticationProvider {
    UserService userService;

    @Autowired
    public Authenticator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (this.userService.login(name, password)) {
            GrantedAuthority[] authorities = { new SimpleGrantedAuthority("ROLE_USER") };

            if (userService.getUser(name).isRoot()) {
                authorities[0] = new SimpleGrantedAuthority("ROLE_ADMIN");
            }
            UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(name, password, Arrays.asList(authorities));
            return t;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
