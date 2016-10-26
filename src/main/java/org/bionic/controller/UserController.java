package org.bionic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User editUserPage(@RequestBody User user, @PathVariable Long id) {
		if (user != null)
			userService.update(user, id);
		return user;
	}
	
	// delete user
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUserPage(@PathVariable Long id) {
		User user = userService.delete(id);
		return user;
	}

	// show user by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public @ResponseBody User showUserPage(@PathVariable Long id) {
		User user = userService.findById(id);
		return user;
	}

	// show all users
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<User> showAllUsers() {
		List<User> users = userService.findAll();
		return users;
	}
	
}

