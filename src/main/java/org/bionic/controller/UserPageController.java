package org.bionic.controller;

import org.bionic.entity.User;
import org.bionic.service.EventService;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Alex Sanak on 17.11.2016.
 */
@Controller
@RequestMapping(value = "/home")
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String goToReg(Model model, Principal principal) {
        if (principal != null) {
            if (userService.getCurrentUser().getRang().getRangName().equals("ADMIN")) {
                return "redirect:/admin";
            }
            return "redirect:/home/workField";
        }
        return "redirect:/registration/registration";
    }

    @RequestMapping(value = "/workField", method = RequestMethod.GET)
    public String showStartOffice(Model model, Principal principal) {
        User loggedUser = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", loggedUser);
        return "home/workField";
    }

    @RequestMapping(value = "/privateOffice", method = RequestMethod.GET)
    public String showPrivateOffice(Model model, Principal principal) {
        User loggedUser = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", loggedUser);
        return "home/privateOffice";
    }

    @RequestMapping(value = "/ourEvents", method = RequestMethod.GET)
    public String showOurEvents(Model model, Principal principal) {
        User loggedUser = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", loggedUser);
        //-----------------Here should be ourEvents service implementation--------------------//
        model.addAttribute("events", eventService.findAll());
        return "home/ourEvents";
    }
}
