package be.kdg.prog4.tdd.backend.service;

import be.kdg.prog4.tdd.backend.dao.UserDao;
import be.kdg.prog4.tdd.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    private final String root;
    private final String pwd;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;

        this.root = "root";
        this.pwd = this.passwordEncoder.encode("rootpasswd");

        this.userDao.create(new User(this.root, this.pwd, true));
    }

    public void addUser(String rootName, String rootPassword, String username, String password) {
        if (this.validate(rootName, rootPassword, true)) {
            User user = new User(username, this.passwordEncoder.encode(password));
            this.userDao.create(user);
        }
    }

    public User getUser(String username) { return this.userDao.read(username); }
    public List<String> getUsers() {
        List<String> users = this.userDao.read();
        users.remove("root");
        return users;
    }

    public void removeUser(String rootName, String rootPassword, String username) {
        if (this.validate(rootName, rootPassword, true)) {
            this.userDao.delete(username);
        }
    }

    public boolean isRoot(String username) {
        return this.isUser(username) ? this.getUser(username).isRoot() : false;
    }
    public boolean isUser(String username) {
        return this.getUser(username) != null;
    }

    public boolean validate(String username, String password) {
        if (!this.isUser(username)) { return false; }

        User user = this.getUser(username);

        return this.passwordEncoder.matches(password, user.getPassword());
    }
    public boolean validate(String username, String password, boolean isRoot) {
        if (this.isRoot(username) != isRoot) { return false; }

        return this.validate(username, password);
    }
}
