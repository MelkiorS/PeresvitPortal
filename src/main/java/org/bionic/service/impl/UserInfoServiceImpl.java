package org.bionic.service.impl;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.bionic.service.UserInfoService;
import org.bionic.dao.UserInfoRepository;
import org.bionic.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserInfo create(UserInfo userInfo, Long userId) {
		userInfo.setUser(userRepository.getOne(userId));
		return userInfoRepository.save(userInfo);
	}

	@Override
	public List<UserInfo> findAll(Long userId) {
		return userInfoRepository.findByUser_id(userId);
	}

	@Override
	public UserInfo update(UserInfo userInfo, Long id) {
		userInfo.setId(id);
		UserInfo updatedUserInfo = userInfoRepository.findOne(id);
		org.springframework.beans.BeanUtils.copyProperties(userInfo, updatedUserInfo);
		return userInfoRepository.save(updatedUserInfo);
	}

	@Override
	public UserInfo findById(Long id) {
		return userInfoRepository.findOne(id);
	}

	@Override
	public UserInfo delete(Long id) {
		UserInfo deletedUserInfo = userInfoRepository.findOne(id);
		userInfoRepository.delete(deletedUserInfo);
		return deletedUserInfo;
	}



}
