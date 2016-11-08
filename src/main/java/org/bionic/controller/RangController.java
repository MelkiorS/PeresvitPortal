package org.bionic.controller;

import java.util.List;

import org.bionic.entity.Rang;
import org.bionic.service.RangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/rang")
public class RangController {
	@Autowired
	private RangService rangService;
		
	//go to manage page
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String goToManagement(Model model) {
		return "admin/rang/rangManagement";
	}
	//go to addForm
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAddForm(Model model) {
		model.addAttribute(new Rang());
		return "admin/rang/addRang";
	}
	
	 // create rang
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createRang(Rang rang, RedirectAttributes model) {
		rangService.save(rang);
		model.addAttribute("rangId", rang.getRangId());
		model.addFlashAttribute("rang", rang);
		return "redirect:/admin/rang/{rangId}";
	}
	
	// show rang by id
	@RequestMapping(value = "/{rangId}", method = RequestMethod.GET)
	public String getRang(@PathVariable("rangId") long rangId, Model model) {
		if (!model.containsAttribute("rang")) 
			model.addAttribute("rang", rangService.findOne(rangId));
		return "admin/rang/rangProfile";
	}

	// show all rangs
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listAllRangs(Model model) {
		List<Rang> rangs = rangService.findAll();
		model.addAttribute("rangList", rangs);
		model.addAttribute("rang", new Rang());
		return "admin/rang/allRangs";
	}
	
	// delete rang
	// need to solve issue when its FK to smth !!!!
	@RequestMapping(value = "/delete/{rangId}", method = RequestMethod.GET)
    public String deleteRang(@PathVariable("rangId") long rangId,
    		Model model) {
        Rang rang = rangService.findOne(rangId);
        if (rang == null) {
           // custom exception
        }
        rangService.delete(rang);
        return listAllRangs(model);
    }
	
	// edit rang
	@RequestMapping(value = "/edit/{rangId}", method = RequestMethod.GET)
    public String editRang(@PathVariable("rangId")  long rangId,
    		Model model) {
        Rang rang = rangService.findOne(rangId);
        if (rang == null) {
           // custom exception
        }
        model.addAttribute("rang", rang);
        return "admin/rang/addRang";
    }
	
}
