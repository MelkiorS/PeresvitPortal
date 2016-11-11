package org.bionic.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

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
//		return principal != null ? "workField/office" : "registration/registration";
	    return "registration/registration";
	}
}
