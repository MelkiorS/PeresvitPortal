package ua.peresvit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.peresvit.entity.CombatArt;
import ua.peresvit.service.CombatArtService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/combatArt")
public class CombatArtController {

    @Autowired
    private CombatArtService combatArtService;

    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/combatArt/combatArtManagement";
    }

    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        CombatArt art = new CombatArt();

        model.addAttribute(art);

        return "admin/combatArt/addCombatArt";
    }

    // create CombatArt
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createArt(CombatArt combatArt, RedirectAttributes model) {

        combatArtService.save(combatArt);

        model.addAttribute("combatArtId", combatArt.getCombatArtId());
        model.addFlashAttribute("combatArt", combatArt);

        return "redirect:/admin/combatArt/{combatArtId}";
    }

    // show CombatArt by id
    @RequestMapping(value = "/{combatArtId}", method = RequestMethod.GET)
    public String geArt(@PathVariable("combatArtId") long combatArtId, Model model) {
        if (!model.containsAttribute("combatArtId"))
            model.addAttribute("combatArtId", combatArtService.findOne(combatArtId));
        return "admin/combatArt/combatArtProfile";
    }

    // show all combatArts
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<CombatArt> arts = combatArtService.findAll();
        model.addAttribute("combatArtList", arts);
        model.addAttribute("combatArt", new CombatArt());
        return "admin/combatArt/allCombatArts";
    }

    // edit combatArt
    @RequestMapping(value = "/edit/{combatArtId}", method = RequestMethod.GET)
    public String editCombatArt(@PathVariable("combatArtId")  long combatArtId,
                           Model model) {
        CombatArt combatArt = combatArtService.findOne(combatArtId);
        model.addAttribute("combatArt", combatArt);
        return "admin/combatArt/addCombatArt";
    }
}
