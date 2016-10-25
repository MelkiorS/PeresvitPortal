package org.bionic.controller;

import java.util.List;

import org.bionic.model.User;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		userService.create(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{userId}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// edit user
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
		User currentUser = userService.findById(userId);
		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User newUser = userService.update(user, userId);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}

	// delete user
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("userId") int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.delete(userId);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

	// show user by id
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// show all users
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> user = userService.findAll();
		if (user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);											
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
}
