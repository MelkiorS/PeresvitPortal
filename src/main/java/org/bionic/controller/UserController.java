package org.bionic.controller;

import java.util.ArrayList;
import java.util.List;

import org.bionic.entity.EnumUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.bionic.entity.User;
import org.bionic.entity.UserInfo;
import org.bionic.service.UserInfoService;
import org.bionic.service.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfoService userInfoService;
	
    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/user/userManagement";
    }
    
    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
    	User user = new User();
    	
    	initializeUserInfo(user);   
       
        model.addAttribute(user);
        
        return "admin/user/addUser";
    }

    // create user
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createUser(User user, RedirectAttributes model, @RequestParam("file") MultipartFile file) {
    	   
    	user.setAvatarURL(userService.saveFile(user, file));
        userService.save(user);
        
    	// UserInfo saving
    	for(UserInfo userInfo : user.getUserInfoList())
    		userInfoService.save(userInfo, user.getUserId());
    	
        model.addAttribute("userId", user.getUserId());
        model.addFlashAttribute("user", user);
        
        return "redirect:/admin/user/{userId}";
    }

    // show user by id
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") long userId, Model model) {
        if (!model.containsAttribute("user"))
            model.addAttribute("user", userService.findOne(userId));
        return "admin/user/userProfile";
    }

    // show all users
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("userList", users);
        model.addAttribute("user", new User());
        return "admin/user/allUsers";
    }

    // delete user
    // need to solve issue when its FK to smth !!!!
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") long userId,
                             Model model) {
        User user = userService.findOne(userId);
        if (user == null) {
            // custom exception
        }
        userService.delete(user);
        return listAllUsers(model);
    }

    // edit user
    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable("userId")  long userId,
                           Model model) {
        User user = userService.findOne(userId);
        if (user == null) {
            // custom exception
        }
        
        initializeUserInfo(user);        	
        
        model.addAttribute("user", user);
        return "admin/user/addUser";
    }

	// initialize UserInfoList    
    private void initializeUserInfo(User user){
    	
    	if(user == null)
    		return;
    	
		if (user.getUserInfoList() == null || user.getUserInfoList().isEmpty()) {
			user.setUserInfoList(new ArrayList<UserInfo>(EnumUserInfo.values().length));
			for (EnumUserInfo value : EnumUserInfo.values())
				user.addUserInfo(new UserInfo(value.name(), ""));
		}
    }
    
}