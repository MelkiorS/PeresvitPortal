package org.bionic.controller;

import java.security.Principal;

import org.bionic.entity.User;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(WebRequest request){
        return "newHome";
    }

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String goToAdminPage(WebRequest request) {
		return "admin/adminIndex";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String goToReg(WebRequest request, Principal principal) {

	    return "redirect:/registration/registration";
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String goToLoginPage(Model model, Principal principal) {
		if (principal != null) {
			User loggedUser = userService.findUserByEmail(principal.getName());
			model.addAttribute("user", loggedUser);
			return "workField/office";
		}
		return "login/login";
	}
}
