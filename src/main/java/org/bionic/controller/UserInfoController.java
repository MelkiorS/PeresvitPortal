package org.bionic.controller;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.bionic.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{id}/info")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	// create userInfo
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public UserInfo addUserInfo(@PathVariable Long id, @RequestBody UserInfo userInfo) {
		return userInfoService.create(userInfo, id);
	}
	
	// show all user info
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> showAllUserInfo(@PathVariable Long id) {
		List<UserInfo> userInfo = userInfoService.findAll(id);
		return userInfo;
	}
	
	// edit user info
	@RequestMapping(value = "/{infoid}", method = RequestMethod.PUT)
	public @ResponseBody UserInfo editUserInfo(@RequestBody UserInfo userInfo, @PathVariable Long infoid) {
		if (userInfo != null)
			userInfoService.update(userInfo, infoid);
		return userInfo;
	}
	
	// show user info by id
	@RequestMapping(value = "/{infoid}", method = RequestMethod.GET)	
	public @ResponseBody UserInfo showUserInfo(@PathVariable Long infoid) {
		UserInfo userInfo = userInfoService.findById(infoid);
		return userInfo;
	}
	
	// delete user info
	@RequestMapping(value = "/{infoid}", method = RequestMethod.DELETE)
	public @ResponseBody UserInfo deleteUserInfo(@PathVariable Long infoid) {
		UserInfo userInfo = userInfoService.delete(infoid);
		return userInfo;
	}	
	
}
