//package be.kdg.chat.prog4.tdd.frontend.config.security;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import be.kdg.chat.prog4.tdd.backend.service.UserService;
//
//@Component("AuthenticationHandler")
//public class AuthenticationHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        String username = userDetails.getUsername();
//        String password = userDetails.getPassword();
//
//        String url = "/tdd";
//        if (this.userService.validate(username, password)) {
//            if (this.userService.isRoot(username)) {
//                url = "/add-user";
//            } else {
//                url = "/add-favorite";
//            }
//        }
//
//        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//        redirectStrategy.sendRedirect(request, response, url);
//    }
//}