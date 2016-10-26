package org.bionic.service;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.springframework.transaction.annotation.Transactional;

public interface UserInfoService {

	@Transactional
	public UserInfo create(UserInfo  UserInfo, Long userId);
	@Transactional
	public UserInfo delete(Long id);
	public List<UserInfo> findAll(Long user);
	@Transactional
	public UserInfo update(UserInfo user, Long id);
	public UserInfo findById(Long id);	
}
