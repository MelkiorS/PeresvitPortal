package org.bionic.controller;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.bionic.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user/{userId}/info")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	// create userInfo
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserInfo(@PathVariable Long userId, @RequestBody UserInfo userInfo, UriComponentsBuilder ucBuilder) {
		userInfoService.create(userInfo, userId);
		
		HttpHeaders headers = new HttpHeaders();
		Object[] ids = {userId,  userInfo.getUserInfoId()};
		headers.setLocation(ucBuilder.path("/user/{userId}/info/{userInfoId}").buildAndExpand(ids).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// show all user info
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<UserInfo>> showAllUserInfo(@PathVariable Long userId) {
		List<UserInfo> userInfo = userInfoService.findAll(userId);
		if (userInfo.isEmpty()) 
			return new ResponseEntity<List<UserInfo>>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<UserInfo>>(userInfo, HttpStatus.OK);
	}

	// edit user info
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.PUT)
	public ResponseEntity<UserInfo> editUserInfo(@RequestBody UserInfo userInfo, @PathVariable Long userInfoId) {
		if (userInfo == null)
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<UserInfo>(userInfoService.update(userInfo, userInfoId), HttpStatus.OK);
	}

	// show user info by userInfoId
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> showUserInfo(@PathVariable Long userInfoId) {
		UserInfo userInfo = userInfoService.findByUserInfoId(userInfoId);
		
		if (userInfo == null) 
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
	}

	// delete user info
	@RequestMapping(value = "/{userInfoId}", method = RequestMethod.DELETE)
	public ResponseEntity<UserInfo> deleteUserInfo(@PathVariable Long userInfoId) {
		userInfoService.delete(userInfoId);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}

}
