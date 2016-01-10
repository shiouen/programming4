package be.kdg.prog4.tdd.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.kdg.prog4.tdd.backend.model.User;
import be.kdg.prog4.tdd.backend.service.FavoriteService;
import be.kdg.prog4.tdd.backend.service.LoginService;

@Controller
public class FavoritesController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/add-favorite", method = RequestMethod.GET)
    public ModelAndView addFavorite(ModelAndView modelAndView) {
        User user = this.loginService.getPrincipal();

        modelAndView.setViewName("add-favorite");
        modelAndView.addObject("user", user);
        modelAndView.addObject("favorites", this.favoriteService.getFavorites(user.getUsername(), user.getPassword()));

        return modelAndView;
    }

    @RequestMapping(value = "/add-favorite", method = RequestMethod.POST)
    public ModelAndView addFavorite(@RequestParam(value = "favorite", required = true) String favorite,
                                    ModelAndView modelAndView) {
        User user = this.loginService.getPrincipal();

        this.favoriteService.addFavorite(user.getUsername(), user.getPassword(), favorite);
        modelAndView.setViewName("redirect:/add-favorite");

        return modelAndView;
    }
}
