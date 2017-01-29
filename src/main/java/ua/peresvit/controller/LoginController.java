package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.peresvit.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @RequestMapping(value = "login/success")
    public String loginSuccess() {
        if (userService.getCurrentUser().getRole().getRoleName().equals("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/home/workField";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("message", messages.getMessage("message.loginFailure", null, httpServletRequest.getLocale()));
        return "home";
    }
}
