package org.bionic.service;


import org.bionic.entity.User;
import org.bionic.entity.VerificationToken;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {

	<S extends User> S save(S arg0);
	
	String saveFile(User user, MultipartFile file);
	
	User findOne(Long userId);

	java.util.List<User> findAll();

	void delete(User user);

	boolean equals(Object obj);

	User findUserByEmail(String email);
	User findUserByEmailAndPassword(String email,String password);
	////////////////////////////////////////////////////////////////////////
	void createVerificationTokenForUser(User user, String token);
	VerificationToken getVerificationToken(String VerificationToken);
	VerificationToken generateNewVerificationToken(String token);
	User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
}
