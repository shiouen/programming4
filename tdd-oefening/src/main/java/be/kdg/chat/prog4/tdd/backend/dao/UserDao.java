package be.kdg.chat.prog4.tdd.backend.dao;

import java.util.List;

import be.kdg.chat.prog4.tdd.backend.model.User;

public interface UserDao {
    void create(User user);
    User read(String username);
    List<String> read();
    void delete(String username);
}
