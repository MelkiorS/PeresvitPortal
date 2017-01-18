package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.peresvit.entity.Achievement;
import ua.peresvit.service.AchievementService;
import ua.peresvit.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/achievement")
public class AchievementController {
    
    @Autowired
    private AchievementService achievementService;

    @Autowired
    private UserService userService;

    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/achievement/achievementManagement";
    }

    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        Achievement achievement = new Achievement();

        model.addAttribute(achievement);

        return "admin/achievement/addAchievement";
    }

    // create achievement
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createAchievement(Achievement achievement, RedirectAttributes model) {

        achievementService.save(achievement);

        model.addAttribute("achievementId", achievement.getAchievementId());
        model.addFlashAttribute("achievement", achievement);

        return "redirect:/admin/achievement/{achievementId}";
    }

    // show Achievement by id
    @RequestMapping(value = "/{achievementId}", method = RequestMethod.GET)
    public String geAchievement(@PathVariable("achievementId") long achievementId, Model model) {
        if (!model.containsAttribute("achievementId"))
            model.addAttribute("achievementId", achievementService.findOne(achievementId));
        return "admin/achievement/achievementProfile";
    }

    // show all achievements
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<Achievement> achievements = achievementService.findAll();
        model.addAttribute("achievementList", achievements);
        model.addAttribute("achievement", new Achievement());
        return "admin/achievement/allAchievements";
    }

    // edit achievement
    @RequestMapping(value = "/edit/{achievementId}", method = RequestMethod.GET)
    public String editAchievement(@PathVariable("achievementId")  long achievementId,
                           Model model) {
        Achievement achievement = achievementService.findOne(achievementId);
        model.addAttribute("achievement", achievement);
        return "admin/achievement/addAchievement";
    }

    //go to achievement addForm
    @RequestMapping(value = "/add/{userId}", method = RequestMethod.GET)
    public String goToAddForm(@PathVariable("userId") long userId, Model model) {
        Achievement achievement = new Achievement();
        achievement.setUser(userService.findOne(userId));
        model.addAttribute("achievement", achievement);
        return "admin/achievement/addAchievement";
    }

    //go to user editForm
    @RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
    public String goToUserForm(Achievement achievement, RedirectAttributes model, @PathVariable("userId") long userId,
                               @RequestParam("file") MultipartFile file) {

        achievement.setImageURL(achievementService.saveFile(achievement, file));
        achievement.setUser(userService.findOne(userId));
        achievementService.save(achievement);
        return "redirect:/admin/user/edit/" + userId;
    }

}