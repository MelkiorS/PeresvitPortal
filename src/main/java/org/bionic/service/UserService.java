package org.bionic.service;

import java.util.List;

import org.bionic.entity.User;
import org.bionic.entity.VerificationToken;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {

	@Transactional
	User create(User user);
	@Transactional
	User delete(Long userId);
	List<User> findAll();
	@Transactional
	User update(User user, Long userId);
	User findByUserId(Long userId);
	User findUserByEmail(String email);
	void createVerificationTokenForUser(User user, String token);
	VerificationToken getVerificationToken(String VerificationToken);
	VerificationToken generateNewVerificationToken(String token);
	@Transactional
	User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
}
