package be.kdg.prog4.tdd.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kdg.prog4.tdd.backend.model.User;

@Service
public class LoginService {
    @Autowired
    private UserService userService;

    private User principal;

    public User getPrincipal() { return this.principal; }

    public boolean login(String username, String password) {
        boolean success = this.userService.validate(username, password);
        this.principal = new User(username, password, (this.userService.isRoot(username) ? true : false));
        return success;
    }
}
