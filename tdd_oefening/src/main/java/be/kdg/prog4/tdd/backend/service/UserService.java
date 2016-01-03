package be.kdg.prog4.tdd.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kdg.prog4.tdd.backend.model.User;
import be.kdg.prog4.tdd.backend.dao.UserDao;

@Service
public class UserService {
    private UserDao userDao;

    private static User principal;

    private final String root = "root";
    private final String pwd = "rootpasswd";

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
        this.userDao.create(new User(this.root, this.pwd, true));
    }

    public void addUser(String rootName, String rootPassword, String username, String password) {
        if (rootName.equals(this.root) && rootPassword.equals(this.pwd)) {
            User user = new User(username, password);
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
        if (rootName.equals(this.root) && rootPassword.equals(this.pwd)) {
            this.userDao.delete(username);
        }
    }

    public User getPrincipal() { return principal; }

    public boolean isRoot(String username) {
        return this.isUser(username) ? this.getUser(username).isRoot() : false;
    }
    public boolean isUser(String username) {
        return this.getUser(username) != null;
    }

    public boolean login(String username, String password) {
        boolean succes = this.validate(username, password);
        principal = this.getUser(username);
        return succes;
    }

    public boolean validate(String username, String password) {
        User user = this.getUser(username);
        if (user == null) { return false; }
        if (!user.getPassword().equals(password)) { return false; }
        return true;
    }
}
