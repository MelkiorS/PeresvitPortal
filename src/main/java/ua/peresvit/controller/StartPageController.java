package ua.peresvit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartPageController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/home";
    }

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String goToAdminPage() {
		return "admin/workFieldAdmin";
	}
}
