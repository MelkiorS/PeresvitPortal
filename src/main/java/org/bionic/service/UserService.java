package org.bionic.service;


import org.bionic.entity.Rang;
import org.bionic.entity.User;
import org.bionic.entity.VerificationToken;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {

	<S extends User> S save(S arg0);
	
	String saveFile(User user, MultipartFile file);
	
	User findOne(Long userId);

	List<User> findAll();

	List<User> findByRang(Rang rang);

	void delete(User user);

	boolean equals(Object obj);

	User findUserByEmail(String email);

	User getCurrentUser();

	void initializeUserInfo(User user);
	////////////////////////////////////////////////////////////////////////
//	void createVerificationTokenForUser(User user, String token);
//	VerificationToken getVerificationToken(String VerificationToken);
//	VerificationToken generateNewVerificationToken(String token);
	User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
	User createUserFromDto(UserDto accountDto);
    User findUserByEmailAndPassword(String email,String password);
}
