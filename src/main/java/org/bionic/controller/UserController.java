package org.bionic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.bionic.entity.User;
import org.bionic.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		return userService.create(user);
	}

	// edit user
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public @ResponseBody User editUserPage(@RequestBody User user, @PathVariable Long userId) {
		if (user != null)
			userService.update(user, userId);
		return user;
	}

	// delete user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUserPage(@PathVariable Long userId) {
		return userService.delete(userId);
	}

	// show user by userId
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public @ResponseBody User showUserPage(@PathVariable Long userId) {
		return userService.findByUserId(userId);
	}

	// show all users
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	public @ResponseBody List<User> showAllUsers() {
		return userService.findAll();
	}

}

