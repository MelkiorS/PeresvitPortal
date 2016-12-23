package ua.peresvit.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.peresvit.entity.User;
import ua.peresvit.entity.UserGroup;
import ua.peresvit.service.UserGroupService;
import ua.peresvit.service.UserService;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by maximmaximov_2 on 21.12.16.
 */
@Controller
@RequestMapping(value = "/admin/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "users", new CustomCollectionEditor(Set.class) {

            protected Object convertElement(Object element) {
                if (element instanceof String) {
                    User user = userService.findOne(Long.parseLong((String)element));
                    return user;
                }
                return null;
            }
        });
    }

    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/userGroup/userGroupManagement";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<UserGroup> userGroups = userGroupService.findAll();
        model.addAttribute("userGroupList", userGroups);
        return "admin/userGroup/allUserGroups";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("userGroup", new UserGroup());
        return "admin/userGroup/addUserGroup";
    }

    @RequestMapping(value="/edit/{userGroupId}", method = RequestMethod.GET)
    public String getUserGroup(@PathVariable(value="userGroupId") long userGroupId, Model model) {
        model.addAttribute("userList", userService.findAll());
        UserGroup ug = userGroupService.findById(userGroupId);
        model.addAttribute("userGroup", ug);
        return "admin/userGroup/addUserGroup";
    }

    //TODO: rename add to edit
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createUser(UserGroup userGroup, Model model) {
        userGroupService.create(userGroup);
        return "redirect:/admin/userGroup/";
    }

    //TODO: Make DELETE METHOD
    @RequestMapping(value = "/delete/{userGroupId}", method = RequestMethod.GET)
    public String createUser(@PathVariable(value="userGroupId") long userGroupId, Model model) {
        userGroupService.delete(userGroupService.findById(userGroupId));
        return "redirect:/admin/userGroup/";
    }



}
