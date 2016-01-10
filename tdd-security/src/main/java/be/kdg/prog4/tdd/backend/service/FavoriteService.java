package be.kdg.prog4.tdd.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private UserService users;

    public void addFavorite(String username, String password, String favorite) {
        if (this.users.validate(username, password)) {
            this.users.getUser(username).addFavorite(favorite);
        }
    }

    public List<String> getFavorites(String username, String password) {
        if (this.users.validate(username, password)) {
            return this.users.getUser(username).getFavorites();
        }
        return new ArrayList<>(0);
    }

    public void removeFavorite(String username, String password, String favorite) {
        if (this.users.validate(username, password)) {
            this.users.getUser(username).removeFavorite(favorite);
        }
    }
}
