package org.bionic.controller;

import java.util.List;

import org.bionic.model.User;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
	public User addUser(@RequestBody User user) {
		return userService.create(user);
	}
	
	// edit user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public @ResponseBody User editUserPage(@RequestBody User user, @PathVariable Integer id) {
		if (user != null)
			userService.create(user);
		return user;
	}
	
	// delete user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUserPage(@PathVariable Integer id) {
		User user = userService.delete(id);
		return user;
	}

	// show user by id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User showUserPage(@PathVariable Integer id) {
		User user = userService.findById(id);
		return user;
	}

	// show all users
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody List<User> showAllUsers() {
		List<User> users = userService.findAll();
		return users;
	}
}
