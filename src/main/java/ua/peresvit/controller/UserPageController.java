package ua.peresvit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.config.Constant;
import ua.peresvit.entity.*;
import ua.peresvit.service.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(value = "/home")
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ResourceGroupTypeService rgtService;

    @Autowired
    private ResourceGroupTypeChapterService rgtcService;

    @Autowired
    private ArticleService aService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private CombatArtService combatArtService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AchievementService achievementService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String goToReg(Model model, Principal principal) {
        if (principal != null) {
            if (userService.getCurrentUser().getRole().getRoleName().equals("ADMIN")) {
                return "redirect:/admin";
            }
            return "redirect:/home/workField";
        }
        return "redirect:/registration";
    }

    @RequestMapping(value = "/workField", method = RequestMethod.GET)
    public String showStartOffice(Model model, Principal principal) {
        User loggedUser = userService.getCurrentUser();
        String imagePath = loggedUser.getAvatarURL();
        model.addAttribute("imageAvatar", null);
        if (imagePath != null) {
            try {
                model.addAttribute("imageAvatar", Constant.encodeFileToBase64Binary(imagePath));
            } catch (IOException ex) {
            }
        }

        // Achievements
        Map<Long, String> achiveList = new HashMap<>();
        List<Achievement> achievements = achievementService.findByUser(loggedUser);
        for (Achievement achievement:achievements) {
            try {
                achiveList.put(achievement.getAchievementId(), Constant.encodeFileToBase64Binary(achievement.getImageURL()));
            } catch (IOException ex){achiveList.put(achievement.getAchievementId(), null);}
        }
        model.addAttribute("achieveList", achiveList);

        List<City> cities = cityService.findAll();
        model.addAttribute("cityList", cities);          // adding list of city for select

        List<Club> clubs = clubService.findAll();
        model.addAttribute("clubList", clubs);           // adding list of club for select

        List<CombatArt> combatArts = combatArtService.findAll();
        model.addAttribute("combatArtList", combatArts); // adding list of combatArt for select

        model.addAttribute("user", loggedUser);
        model.addAttribute("groups", rgtService.findAll());

        return "home/workField";
    }

     // go to Edit user form
    @RequestMapping(value = "/profileEdit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable("userId")  long userId, Model model) {
        User user = userService.findOne(userId);

        List<City> cities = cityService.findAll();
        model.addAttribute("cityList", cities);          // adding list of city for select

        List<Club> clubs = clubService.findAll();
        model.addAttribute("clubList", clubs);           // adding list of club for select

        List<CombatArt> combatArts = combatArtService.findAll();
        model.addAttribute("combatArtList", combatArts); // adding list of combatArt for select

        List<User> mentors = userService.findByRole( roleService.findOne(4l));
        model.addAttribute("mentorList", mentors);       // adding list of mentor for select

        List<Role> roleList = roleService.findAll();
        model.addAttribute("rangList", roleList);       // adding list of rang for select

        model.addAttribute(user);

         return "home/profileEdit";
    }

    // Edit User
    @RequestMapping(value = "/profileEdit", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) return "home/profileEdit";

        user.setAvatarURL(userService.saveFile(user, file));

        // check fields
        if (user.getCity().getCityId() == null)
            user.setCity(null);
        if (user.getClub().getClubId() == null)
            user.setClub(null);
        if (user.getCombatArt().getCombatArtId() == null)
            user.setCombatArt(null);
        if (user.getMentor().getUserId() == null)
            user.setMentor(null);

        userService.save(user);

        model.addAttribute("user", user);
        return "redirect:/home/workField";
    }

    @RequestMapping(value = "/privateOffice", method = RequestMethod.GET)
    public String showPrivateOffice(Model model, Principal principal) {
        User loggedUser = userService.findUserByEmail(principal.getName());

        String imagePath = loggedUser.getAvatarURL();
        model.addAttribute("imageAvatar", null);
        try {
            model.addAttribute("imageAvatar", Constant.encodeFileToBase64Binary(imagePath));
        }catch (IOException ex){}
        model.addAttribute("user", loggedUser);

        return "home/privateOffice";
    }

    @RequestMapping(value = "/ourEvents", method = RequestMethod.GET)
    public String showOurEvents(Model model, Principal principal) {
        model.addAttribute("user", userService.getCurrentUser());
        return "home/ourEvents";
    }

//    @RequestMapping(value = "/myWay/myWayChapters/{groupName}")
//    public String myWayChapters(Model model, @PathVariable(value = "groupName") String groupName) {
//        model.addAttribute("user", userService.getCurrentUser());
//        ResourceGroupType rgt = rgtService.findResourceGroupTypeByGroupName(groupName);
//        model.addAttribute("chapters", rgt.getChapterList());
//        return "home/myWay/myWayChapters";
//    }

    @RequestMapping(value = "/myWay/myWayChapters/{groupName}/{articleId}")
    public String myWayChapters(Model model, @PathVariable(value = "groupName") String groupName, @PathVariable(value = "articleId") long articleId) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("article", aService.findOne(articleId));
        return "home/myWay/myWayArticle";
    }

    //TODO: front - сделать авто пересчет в случае успеха - автоматом отражать новое состояние, в случае неудачи - показывать сообщение о фейле
    //TODO: back - добавить функцию удаления unassign
    //TODO: сделать рекфакторинг EventService isAssignedToMe - переделать на обращение к методу сущности. дописать тесты
    @RequestMapping(value = "/myWay/assignToMe", method = RequestMethod.POST)
    public boolean assignToMe(Model model, @RequestParam(value = "eventId") long eventid) {
        Event ev = eventService.findById(eventid);
        return eventService.assignToMe(ev);
    }

    @RequestMapping(value = "/myWay/isAssignedToMe", method = RequestMethod.GET)
    public boolean isAssignedToMe(Model model, @RequestParam(value = "eventId") long eventid) {
        Event ev = eventService.findById(eventid);
        return eventService.isAssignedToMe(ev);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showAllMessages() {
        return "home/messages";
    }
}
