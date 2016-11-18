package org.bionic.controller;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.bionic.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user/{userId}/info")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	// create userInfo
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public UserInfo addUserInfo(@PathVariable Long userId, @RequestBody UserInfo userInfo) {
		return userInfoService.save(userInfo, userId);
	}

	// show all user info
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> showAllUserInfo(@PathVariable Long userId) {
		List<UserInfo> userInfo = userInfoService.findAll(userId);
		return userInfo;
	}

	// edit user info
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.PUT)
	public @ResponseBody UserInfo editUserInfo(@RequestBody UserInfo userInfo, @PathVariable Long userInfoId) {
		if (userInfo != null)
			userInfoService.update(userInfo, userInfoId);
		return userInfo;
	}

	// show user info by userInfoId
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.GET)
	public @ResponseBody UserInfo showUserInfo(@PathVariable Long userInfoId) {
		UserInfo userInfo = userInfoService.findByUserInfoId(userInfoId);
		return userInfo;
	}

	// delete user info
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.DELETE)
	public @ResponseBody UserInfo deleteUserInfo(@PathVariable Long userInfoId) {
		UserInfo userInfo = userInfoService.delete(userInfoId);
		return userInfo;
	}

}
