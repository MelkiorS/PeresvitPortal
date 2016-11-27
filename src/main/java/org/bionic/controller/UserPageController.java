package org.bionic.controller;

import org.apache.commons.io.FileUtils;
import org.bionic.config.Constant;
import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.User;
import org.bionic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

/**
 * Created by Alex Sanak on 17.11.2016.
 */
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String goToReg(Model model, Principal principal) {
        if (principal != null) {
            if (userService.getCurrentUser().getRang().getRangName().equals("ADMIN")) {
                return "redirect:/admin";
            }
            return "redirect:/home/workField";
        }
        return "redirect:/registration/registration";
    }

    @RequestMapping(value = "/workField", method = RequestMethod.GET)
    public String showStartOffice(Model model, Principal principal) {
        User loggedUser = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", loggedUser);
        model.addAttribute("groups", rgtService.findAll());

        return "home/workField";
    }

     // go to Edit user form
    @RequestMapping(value = "/privateOfficeEdit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable("userId")  long userId, Model model) {
        User user = userService.findOne(userId);
        model.addAttribute(user);

         return "home/privateOfficeEdit";
    }

    // Edit User
    @RequestMapping(value = "/privateOfficeEdit", method = RequestMethod.POST)
    public String editUser(User user, Model model, @RequestParam("file") MultipartFile file) {

        user.setAvatarURL(userService.saveFile(user, file));
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

    @RequestMapping(value = "/myWay/myWayChapters/{groupName}")
    public String myWayChapters(Model model, @PathVariable(value = "groupName") String groupName) {
        model.addAttribute("user", userService.getCurrentUser());
        ResourceGroupType rgt = rgtService.findResourceGroupTypeByGroupName(groupName);
        model.addAttribute("chapters", rgt.getChapterList());
        return "home/myWay/myWayChapters";
    }

    @RequestMapping(value = "/myWay/myWayChapters/{groupName}/{articleId}")
    public String myWayChapters(Model model, @PathVariable(value = "groupName") String groupName, @PathVariable(value = "articleId") long articleId) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("article", aService.findOne(articleId));
        return "home/myWay/myWayArticle";
    }
}
