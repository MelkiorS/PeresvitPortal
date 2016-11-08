package org.bionic.controller;

import java.util.List;

import org.bionic.validation.EmailExistsException;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import org.bionic.entity.User;
import org.bionic.service.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// Show registration form
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registration/registration";
	}

	// create user
	@CrossOrigin
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(
			@ModelAttribute("user") UserDto accountDto,
			BindingResult result,
			WebRequest request,
			Errors errors) {

		User registered = userService.registerNewUserAccount(accountDto);
		if (!result.hasErrors()) {
			registered = createUserAccount(accountDto, result);
		}
		if (registered == null) {
			result.rejectValue("email", "message.regError");
		}
		if (result.hasErrors()) {
			return new ModelAndView("registration/registration", "user", accountDto);
		}
		else {
			return new ModelAndView("workField/office", "user", accountDto);
		}
	}
	private User createUserAccount(UserDto accountDto, BindingResult result) {
		User registered = null;
		try {
			registered = userService.registerNewUserAccount(accountDto);
		} catch (UserAlreadyExistException e) {
			return null;
		}
		return registered;
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