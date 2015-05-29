package com.levelup.stock.view;

import com.levelup.stock.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/welcome")
@SessionAttributes("user")
public class WelcomeController {

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String viewWelcomePage(Model model, User user) {

        model.addAttribute("user", new User());
        return "welcome.page";
    }

    @RequestMapping(value ="/mainPage", method = RequestMethod.GET)
    public String viewMainPage(Model model, @ModelAttribute("user") User user) {
        return "main.page";
    }

}
