//package be.kdg.chat.prog4.tdd.frontend.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import be.kdg.chat.prog4.tdd.backend.service.UserService;
//
///**
// * Configures web security and rest api security aspects.
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class MultiHttpSecurityConfig  {
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
//    }
//
//    @Configuration
//    @Order(1)
//    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.requestMatcher(request -> {
//                final String url = request.getServletPath() + request.getPathInfo();
//                return !(url.startsWith("/"));
//            });
//
//            http.authorizeRequests().antMatchers("/tdd").permitAll()
//                    .antMatchers("/add-user").access("hasRole('ROLE_ADMIN')")
//                    .antMatchers("/add-favorite").access("hasRole('ROLE_USER')")
//                    .anyRequest().authenticated()      // remaining URL's require authentication
//                    .and()
//                    .formLogin()
//                    .and()
//                    .csrf();
//        }
//    }
//}