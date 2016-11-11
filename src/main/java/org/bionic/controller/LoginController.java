package org.bionic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex Sanak on 11.11.2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "login")
    public String login() {
        return "login/login";
    }
}
