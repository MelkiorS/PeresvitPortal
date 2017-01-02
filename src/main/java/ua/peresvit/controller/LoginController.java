package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.peresvit.service.UserService;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login/success")
    public String loginSuccess() {
        if (userService.getCurrentUser().getRole().getRoleName().equals("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/home/workField";
    }


}
