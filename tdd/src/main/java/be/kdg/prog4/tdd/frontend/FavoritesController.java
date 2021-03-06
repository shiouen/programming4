package be.kdg.prog4.tdd.frontend;

import be.kdg.prog4.tdd.backend.service.FavoriteService;
import be.kdg.prog4.tdd.backend.service.UserService;
import be.kdg.prog4.tdd.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FavoritesController {
    private final FavoriteService favoriteService;
    private final UserService userService;

    @Autowired
    public FavoritesController(FavoriteService favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add-favorite", method = RequestMethod.GET)
    public ModelAndView addFavorite(ModelAndView modelAndView) {
        User user = this.userService.getPrincipal();

        modelAndView.setViewName("add-favorite");
        modelAndView.addObject("user", user);
        modelAndView.addObject("favorites", this.favoriteService.getFavorites(user.getUsername(), user.getPassword()));

        return modelAndView;
    }

    @RequestMapping(value = "/add-favorite", method = RequestMethod.POST)
    public ModelAndView addFavorite(@RequestParam(value = "favorite", required = true) String favorite,
                                    ModelAndView modelAndView) {
        User user = this.userService.getPrincipal();
        this.favoriteService.addFavorite(user.getUsername(), user.getPassword(), favorite);
        modelAndView.setViewName("redirect:/add-favorite");
        return modelAndView;
    }
}
