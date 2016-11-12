package org.bionic.controller;

import java.security.Principal;

import org.bionic.entity.User;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(WebRequest request ,Model model){
		model.addAttribute("user", new User());
		return "signIn";
    }

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String goToAdminPage(WebRequest request) {
		return "admin/adminIndex";
	}


	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String goToUserPage(User user, Model model, HttpServletRequest request) {
//		return principal != null ? "workField/office" : "registration/registration";
		HttpSession session = request.getSession();
		User userToSignIn = userService.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
		if (userToSignIn == null)
		{
			return "signIn";
		}
		else if (userToSignIn != null && userToSignIn.getRang().getRangName().equals("ROLE_ADMIN"))
		{
			session.setAttribute("admin", userToSignIn);
			return "admin/adminIndex";
		}
		session.setAttribute("user", userToSignIn);
		model.addAttribute("user", userToSignIn);
		return "workField/office";
	}


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String goToReg(WebRequest request, Principal principal) {
//		return principal != null ? "workField/office" : "registration/registration";
	    return "redirect:/registration/registration";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLoginPage(WebRequest request) {
		return "login/login";
	}
}
