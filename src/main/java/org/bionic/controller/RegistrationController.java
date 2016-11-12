package org.bionic.controller;

import org.bionic.entity.User;
import org.bionic.service.RangService;
import org.bionic.service.UserService;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Alex Sanak on 10.11.2016.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RangService rangService;


    // Show registration form
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration/registration";
    }

    // register user
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(HttpServletRequest request, User user, Model model) {
        user.setRang(rangService.findOne(2l));
        userService.save(user);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("user", user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "workField/office";
    }
//        if (!result.hasErrors()) {
//            System.out.println("ADDING");
//            registered = createUserAccount(accountDto, result);
//            model.addFlashAttribute("user", registered);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if (result.hasErrors()) {
//            System.out.println("DON'T ADDING");
//            return "registration/registration";
//        }
//        else {
//            model.addAttribute("userId", registered.getUserId());
//            return "redirect:/registration/success/{userId}";
//        }
    @RequestMapping(value = "/success/{userId}", method = RequestMethod.GET)
    public String showPersPage(@PathVariable("userId") long userId, WebRequest request, Model model) {
        if (!model.containsAttribute("user"))
            model.addAttribute("user", userService.findOne(userId));
        return "workField/office";
    }
    private User createUserAccount(UserDto accountDto) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }
}
