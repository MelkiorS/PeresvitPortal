package org.bionic.controller;

import org.bionic.entity.User;
import org.bionic.service.UserService;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Alex Sanak on 10.11.2016.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Show registration form
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration/registration";
    }

    // create user
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("user") UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors,
            Model model) {

        User registered = userService.registerNewUserAccount(accountDto);
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
            model.addAttribute("user", User.class);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return "registration/registration";
        }
        else {
            return "workField/office";
        }
    }
    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }
}
