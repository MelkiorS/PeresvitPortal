package org.bionic.service;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserInfoService {

	public UserInfo save(UserInfo  UserInfo, Long userId);
	public UserInfo save(UserInfo  UserInfo);
	public UserInfo delete(Long userInfoId);
	public List<UserInfo> findAll(Long userId);
	public UserInfo update(UserInfo userInfo, Long userInfoId);
	public UserInfo findByUserInfoId(Long userInfoId);
}
