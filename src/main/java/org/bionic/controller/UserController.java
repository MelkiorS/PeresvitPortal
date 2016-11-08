package org.bionic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import org.bionic.entity.User;
import org.bionic.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		userService.create(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlAllowOrigin("*");
		headers.setLocation(ucBuilder.path("/user/{userId}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);		
	}

	// edit user
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> editUserPage(@RequestBody User user, @PathVariable Long userId) {
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(userService.update(user, userId), HttpStatus.OK);
	}

	// delete user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUserPage(@PathVariable Long userId) {
		userService.delete(userId);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// show user by userId
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> showUserPage(@PathVariable Long userId) {
		User user = userService.findByUserId(userId);
		
		if (user == null) 
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// show all users
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<List<User>> showAllUsers() {
		List<User> userList = userService.findAll();
		
		if (userList.isEmpty()) 
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

}