package org.bionic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StartPageController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/registration";
    }

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String goToAdminPage() {
		return "admin/adminIndex";
	}
}
