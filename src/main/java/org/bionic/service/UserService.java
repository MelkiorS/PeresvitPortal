package org.bionic.service;

import java.util.List;

import org.bionic.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	@Transactional
	public User create(User user);
	@Transactional
	public User delete(Long userId);
	public List<User> findAll();
	@Transactional
	public User update(User user, Long userId);
	public User findByUserId(Long userId);

}
