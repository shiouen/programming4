package be.kdg.prog4.tdd;

import java.util.List;

import be.kdg.prog4.tdd.backend.service.FavoriteService;
import be.kdg.prog4.tdd.config.TestContextConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import be.kdg.prog4.tdd.backend.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContextConfig.class })
public class TestFavorites {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        this.userService.addUser("root", "rootpasswd", "username", "password");
        this.userService.addUser("root", "rootpasswd", "username2", "password2");
    }

    @Test
    public void testGetFavoritesOfExistingUserWithoutFavorites() {
        List<String> favorites = this.favoriteService.getFavorites("username", "password");
        assertNotNull("getFavorites should never return null", favorites);
        assertEquals("there should be no favorites in the list", 0, favorites.size());
    }
    @Test
    public void testGetFavoritesOfExistingUserWithOneFavorite() {
        this.favoriteService.addFavorite("username", "password", "favorite1");
        List<String> favorites = this.favoriteService.getFavorites("username", "password");
        assertEquals("user username should have 1 favorite", 1, favorites.size());
        assertEquals("favorite should be 'favorite1'", "favorite1", favorites.get(0));
        this.favoriteService.removeFavorite("username", "password", "favorite1");
    }
    @Test
    public void testGetFavoritesOfExistingUserWithTwoFavorites() {
        this.favoriteService.addFavorite("username", "password", "a favorite");
        this.favoriteService.addFavorite("username", "password", "another favorite");
        List<String> favorites = this.favoriteService.getFavorites("username", "password");
        assertEquals("user username should have 2 favorites", 2, favorites.size());
        assertTrue("'a favorite' should have been added", favorites.contains("a favorite"));
        assertTrue("'another favorite' should have been added", favorites.contains("another favorite"));
        this.favoriteService.removeFavorite("username", "password", "a favorite");
        this.favoriteService.removeFavorite("username", "password", "another favorite");
    }
    @Test
    public void testGetFavoritesOfNonExistingUser() {
        this.favoriteService.addFavorite("username", "password", "a favorite");
        List<String> favorites = favoriteService.getFavorites("nonexsting", "wrong password");
        assertEquals("there should not be any favorites", 0, favorites.size());
        this.favoriteService.removeFavorite("username", "password", "a favorite");
    }
    @Test
    public void testGetFavoritesFromDifferentUser() {
        this.favoriteService.addFavorite("username", "password", "a favorite");
        this.favoriteService.addFavorite("username2", "password2", "another favorite");
        List<String> favorites = this.favoriteService.getFavorites("username2", "password2");
        assertEquals("user should have 1 favorite", 1, favorites.size());
        assertEquals("favorite should be 'another favorite'", "another favorite", favorites.get(0));
        this. favoriteService.removeFavorite("username", "password", "a favorite");
        this.favoriteService.removeFavorite("username2", "password2", "another favorite");
    }
    @Test
    public void testWrongCredentialsWithAddFavorite() {
        this.favoriteService.addFavorite("username", "wrong password", "a favorite");
        List<String> favorites = this.favoriteService.getFavorites("username", "password");
        assertEquals("should not be able to add favorite with wrong password", 0, favorites.size());
        this.favoriteService.removeFavorite("username", "wrong password", "a favorite");
    }
    @Test
    public void testWrongCredentialsWithRemoveFavorites() {
        this.favoriteService.addFavorite("username", "password", "a favorite");
        this.favoriteService.removeFavorite("username", "wrong password", "a favorite");
        List<String> favorites = this.favoriteService.getFavorites("username", "password");
        assertEquals("should not be able to remove favorite with wrong credentials", 1, favorites.size());
        this.favoriteService.removeFavorite("username", "password", "a favorite");
    }
    @Test
    public void testAddUserWithWrongCredentials() {
        this.userService.addUser("wrong root", "wrong password", "user", "pass");
        boolean checkLogin = this.userService.validate("user", "pass");
        Assert.assertFalse("only root with password rootpasswd can add a user", checkLogin);
        this.userService.addUser("root", "wrong passwd", "user", "pass");
        checkLogin = this.userService.validate("user", "pass");
        Assert.assertFalse("only root with password rootpasswd can add a user", checkLogin);
    }
    @Test
    public void testRemoveUserWithWrongCredentials() {
        this.userService.removeUser("wrong root", "wrong password", "username");
        boolean checkLogin = this.userService.validate("username", "password");
        Assert.assertTrue("only root with password rootpasswd can remove a user", checkLogin);
        this.userService.removeUser("root", "wrong passwd", "username2");
        checkLogin = this.userService.validate("username2", "password2");
        Assert.assertTrue("only root with password rootpasswd can remove a user", checkLogin);
    }
}
