package be.kdg.chat.prog4.tdd.frontend.controller;

import be.kdg.chat.prog4.tdd.backend.model.User;
import be.kdg.chat.prog4.tdd.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.kdg.chat.prog4.tdd.backend.service.FavoriteService;

@Controller
public class UserController {
    private final FavoriteService favoriteService;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public ModelAndView addUser(ModelAndView modelAndView) {
        User user = this.userService.getPrincipal();

        if (this.userService.isRoot(user.getUsername())) {
            modelAndView.setViewName("add-user");
            modelAndView.addObject("user", user);
            modelAndView.addObject("users", this.userService.getUsers());
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "You were not logged in as root.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "password", required = true) String password,
                                ModelAndView modelAndView) {
        User user = this.userService.getPrincipal();

        if (this.userService.isRoot(user.getUsername())) {
            this.userService.addUser(user.getUsername(), user.getPassword(), username, password);
            modelAndView.setViewName("redirect:/add-user");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "You were not logged in as root.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/remove-user", method = RequestMethod.POST)
    public ModelAndView removeUser(@RequestParam(value = "username", required = true) String username,
                                   ModelAndView modelAndView) {
        User user = this.userService.getPrincipal();

        if (this.userService.isRoot(user.getUsername())) {
            this.userService.removeUser(user.getUsername(), user.getPassword(), username);
            modelAndView.setViewName("redirect:/add-user");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "You were not logged in as root.");
        }
        return modelAndView;
    }
}
