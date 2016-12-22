package ua.peresvit.controller;

import ua.peresvit.entity.Mark;
import ua.peresvit.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/mark")
public class MarkController {
    @Autowired
    private MarkService markService;

    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/mark/markManagement";
    }

    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        Mark mark = new Mark();

        model.addAttribute(mark);

        return "admin/mark/addMark";
    }

    // create mark
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createMark(Mark mark, RedirectAttributes model) {

        markService.save(mark);

        model.addAttribute("markId", mark.getMarkId());
        model.addFlashAttribute("mark", mark);

        return "redirect:/admin/mark/{markId}";
    }

    // show Mark by id
    @RequestMapping(value = "/{markId}", method = RequestMethod.GET)
    public String geMark(@PathVariable("markId") long markId, Model model) {
        if (!model.containsAttribute("markId"))
            model.addAttribute("markId", markService.findOne(markId));
        return "admin/mark/markProfile";
    }

    // show all marks
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<Mark> marks = markService.findAll();
        model.addAttribute("markList", marks);
        model.addAttribute("mark", new Mark());
        return "admin/mark/allMarks";
    }

    // edit mark
    @RequestMapping(value = "/edit/{markId}", method = RequestMethod.GET)
    public String editMark(@PathVariable("markId")  long markId,
                           Model model) {
        Mark mark = markService.findOne(markId);
        model.addAttribute("mark", mark);
        return "admin/mark/addMark";
    }

}

