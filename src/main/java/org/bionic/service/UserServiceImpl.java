package org.bionic.service;

import java.util.List;



import org.bionic.model.User;
import org.bionic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User create(User user) {
		User createdUser = user;
		return userRepository.save(createdUser);
	}
	
	@Override
	@Transactional
	public User findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public User delete(int id) {
		User deletedUser = userRepository.findOne(id);
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(User user){
		User updatedUser = userRepository.findOne(user.getId());
		updatedUser.setName(user.getName());
		return updatedUser;
	}

}
