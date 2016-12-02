package org.bionic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bionic.config.Constant;
import org.bionic.dao.RangRepository;
//import org.bionic.dao.VerificationTokenRepository;
import org.bionic.entity.EnumUserInfo;
import org.bionic.entity.User;
import org.bionic.entity.UserInfo;
import org.bionic.entity.VerificationToken;
import org.bionic.service.UserService;
import org.bionic.dao.UserRepository;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

//    @Autowired
//    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RangRepository rangRepository;


    @Override
    public <S extends User> S save(S arg0) {
        return userRepository.save(arg0);
    }

    @Override
    public User findOne(Long userId) {
        User user = userRepository.findOne(userId);
        initializeUserInfo(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean equals(Object obj) {
        return userRepository.equals(obj);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        initializeUserInfo(user);
        return user;
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return userRepository.findByEmail(name);
    }

    @Override
    public void initializeUserInfo(User user) {
        if(user == null)
            return;

        if (user.getUserInfoList() == null || user.getUserInfoList().isEmpty()) {
            user.setUserInfoList(new ArrayList<UserInfo>(EnumUserInfo.values().length));
            for (EnumUserInfo value : EnumUserInfo.values())
                user.addUserInfo(new UserInfo(value.name(), "--------"));
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////

    /*@Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/

    @Transactional
    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRang(rangRepository.findOne(4L));

        return userRepository.save(user);
    }

    @Override
    public User createUserFromDto(UserDto accountDto) {
        final User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRang(rangRepository.findOne(4L));
        return user;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

	@Override
	public String saveFile(User user, MultipartFile inputFile) {
		
		if(inputFile.isEmpty())
			return "";
			
		if(user.getUserId() == null)
			save(user);
			
		String pathFile = Constant.UPLOAD_PATH + "/" + Constant.USR_FOLDER + user.getUserId() + "/" + Constant.AVATAR;
		String fileURL = Constant.uploadingFile(inputFile, pathFile);
		
		// delete old file
		if(user.getUserId() != null){
			String oldPath = findOne(user.getUserId()).getAvatarURL();
			if(!fileURL.equals(oldPath))
				Constant.deleteFile(findOne(user.getUserId()).getAvatarURL());
		}
			
		return fileURL;
	}
    @Override
    public User findUserByEmailAndPassword(String email,String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
