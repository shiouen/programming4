package be.kdg.chat.prog4.tdd.backend.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private final boolean root;

    private final List<String> favorites;

    public User(String username, String password) {
        this(username, password, false);
    }
    public User(String username, String password, boolean root) {
        this.username = username;
        this.password = password;

        this.root = root;

        this.favorites = new ArrayList<>();
    }

    public List<String> getFavorites() { return this.favorites; }
    public String getPassword() { return this.password; }
    public String getUsername() { return this.username; }

    public boolean isRoot() { return this.root; }

    public void addFavorite(String favorite) { this.favorites.add(favorite); }
    public void removeFavorite(String favorite) { this.favorites.remove(favorite); }
}
