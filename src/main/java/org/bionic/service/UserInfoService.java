package org.bionic.service;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.springframework.transaction.annotation.Transactional;

public interface UserInfoService {

	@Transactional
	public UserInfo create(UserInfo  UserInfo, Long userId);
	@Transactional
	public UserInfo delete(Long userInfoId);
	public List<UserInfo> findAll(Long userId);
	@Transactional
	public UserInfo update(UserInfo userInfo, Long userInfoId);
	public UserInfo findByUserInfoId(Long userInfoId);
}
