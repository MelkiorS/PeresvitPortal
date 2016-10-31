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
		return userInfoRepository.findByUserInfoId(userId);
	}

	@Override
	public UserInfo update(UserInfo userInfo, Long userInfoId) {
		UserInfo updatedUserInfo = userInfoRepository.findOne(userInfoId);
		org.springframework.beans.BeanUtils.copyProperties(userInfo, updatedUserInfo);
		return userInfoRepository.save(updatedUserInfo);
	}

	@Override
	public UserInfo findByUserInfoId(Long userInfoId) {
		return userInfoRepository.findOne(userInfoId);
	}

	@Override
	public UserInfo delete(Long userInfoId) {
		UserInfo deletedUserInfo = userInfoRepository.findOne(userInfoId);
		userInfoRepository.delete(deletedUserInfo);
		return deletedUserInfo;
	}

}
