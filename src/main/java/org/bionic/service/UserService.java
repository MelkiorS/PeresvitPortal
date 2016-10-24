package org.bionic.service;

import java.util.List;

import org.bionic.model.User;


public interface UserService {
	
	public User create(User user);
	public User delete(int id);
	public List<User> findAll();
	public User update(User user);
	public User findById(int id);

}
