package be.kdg.prog4.tdd.backend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import be.kdg.prog4.tdd.backend.model.User;

@Repository
public class UserDaoWithMap implements UserDao {
    private Map<String, User> users;

    public UserDaoWithMap() {
        this.users = new HashMap<>();
    }

    @Override
    public void create(User user) {
        this.users.put(user.getUsername(), user);
    }

    @Override
    public User read(String username) {
        return this.users.get(username);
    }
    @Override
    public List<String> read() { return new ArrayList<>(this.users.keySet()); }

    @Override
    public void delete(String username) {
        this.users.remove(username);
    }
}
