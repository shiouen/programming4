package be.kdg.prog4.tdd.frontend.config.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import be.kdg.prog4.tdd.backend.service.UserService;

@Component("AuthenticationHandler")
public class OnAuthenticationSuccesHandler implements AuthenticationSuccessHandler {
    UserService userService;

    @Autowired
    public OnAuthenticationSuccesHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();

        String url = this.userService.isRoot(username) ? "/add-user" : "/add-favorite";

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, url);
    }
}