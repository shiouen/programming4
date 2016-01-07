package be.kdg.chat.prog4.tdd.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public ModelAndView error(ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Wrong username or password.");
        return modelAndView;
    }
}
