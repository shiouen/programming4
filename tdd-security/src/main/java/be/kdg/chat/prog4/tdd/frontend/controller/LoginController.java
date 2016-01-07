package be.kdg.chat.prog4.tdd.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.kdg.chat.prog4.tdd.backend.service.UserService;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password", required = true) String password,
                              ModelAndView modelAndView) {
        boolean validLogin = this.userService.login(username, password);
        boolean loginAsRoot = this.userService.isRoot(username);

        if (validLogin && loginAsRoot) {
            modelAndView.setViewName("redirect:/add-user");
        } else if (validLogin){
            modelAndView.setViewName("redirect:/add-favorite");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Wrong username or password.");
        }
        return modelAndView;
    }
}
