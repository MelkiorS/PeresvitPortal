package org.bionic.controller;

import org.bionic.entity.Rang;
import org.bionic.entity.User;
import org.bionic.service.RangService;
import org.bionic.service.UserService;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
            @ModelAttribute("user") UserDto accountDto, RedirectAttributes model, Principal principal) {

        User registered;
        registered = createUserAccount(accountDto);
        if (registered == null) {
            return "registration/registration";
        }
        model.addFlashAttribute("user", registered);
        authenticateUser(registered);
        return "redirect:/registration/success";
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
    }
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String showPersPage() {
        return "home/workField";
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

    public void authenticateUser(User user) {
        List<GrantedAuthority> authorities =
                new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        authorities));
    }

}
