package org.bionic.service.impl;

import java.util.List;


import org.bionic.entity.User;
import org.bionic.service.UserService;
import org.bionic.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		User createdUser = user;
		return userRepository.save(createdUser);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User delete(Long id) {
		User deletedUser = userRepository.findOne(id);
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(User user, Long id){
		user.setId(id);
		User updatedUser = userRepository.findOne(id);
		org.springframework.beans.BeanUtils.copyProperties(user, updatedUser);
		return userRepository.save(updatedUser);
	}

}
