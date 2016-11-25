package org.bionic.controller;

import org.bionic.entity.User;
import org.bionic.registration.OnRegistrationCompleteEvent;
import org.bionic.security.PasswordGenerator;
import org.bionic.service.UserService;
import org.bionic.web.dto.UserDto;
import org.bionic.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Sanak on 10.11.2016.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Inject
    private ConnectionRepository connectionRepository;

    // Show registration form
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "home";
    }

    // register user
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("user") UserDto accountDto, final HttpServletRequest request) {

        User registered = createUserAccount(accountDto);
        if (registered == null) {
            return "home";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        authenticateUser(registered);
        return "redirect:/home/workField";
    }
//        if (!result.hasErrors()) {
//            System.out.println("ADDING");
//            registered = createUserAccount(accountDto, result);
//            model.addFlashAttribute("user", registered);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if (result.hasErrors()) {
//            System.out.println("DON'T ADDING");
//            return "registration/registration";
//        }
//        else {
//            model.addAttribute("userId", registered.getUserId());
//            return "redirect:/registration/success/{userId}";
//        }
//    @RequestMapping(value = "/facebookRequest", method = RequestMethod.GET)
//    public String verifyFacebookUser(WebRequest request, final HttpServletRequest httpServletRequest){
//        FacebookConnectionFactory facebookConnectionFactory = (FacebookConnectionFactory) connectionFactoryRegistry
//                .getConnectionFactory("facebook");
//        OAuth2Operations oauthOperations = facebookConnectionFactory
//                .getOAuthOperations();
//        oAuth2Parameters.setState("recivedfromfacebooktoken");
//        String authorizeUrl = oauthOperations.buildAuthorizeUrl(
//                GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
//        return authorizeUrl;
//    }
//
//    @RequestMapping(value = "/facebookResponse", method = RequestMethod.GET)
//    public String registerFacebookUser(WebRequest request, final HttpServletRequest httpServletRequest){
//        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
//        UserDto userDto = createSocialUserDto(connection);
//
//        User registered = createUserAccount(userDto);
//
//        if (registered == null) {
//            registered = userService.findUserByEmail(userDto.getEmail());
//            authenticateUser(registered);
//            return "redirect:/home/workField";
//        }
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(httpServletRequest)));
//        authenticateUser(registered);
//        return "redirect:/home/workField";
//    }

    @RequestMapping(value="/facebook", method=RequestMethod.GET)
    public String facebook(WebRequest request, final HttpServletRequest httpServletRequest) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return "redirect:/connect/facebook";
        }
        UserDto userDto = createSocialUserDto(connection);

        User registered = createUserAccount(userDto);

        if (registered == null) {
            registered = userService.findUserByEmail(userDto.getEmail());
            authenticateUser(registered);
            return "redirect:/home/workField";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(httpServletRequest)));
        authenticateUser(registered);
        return "redirect:/home/workField";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String showPersPage() {
        return "redirect:/home/workField";
    }

    private User createUserAccount(UserDto accountDto) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }

    private UserDto createSocialUserDto(Connection<?> connection) {
        if(connection != null) {
            UserProfile facebookUser = connection.fetchUserProfile();
            UserDto userDto = new UserDto();
            userDto.setEmail(facebookUser.getEmail());
            userDto.setFirstName(facebookUser.getFirstName());
            userDto.setLastName(facebookUser.getLastName());
            String password = PasswordGenerator.generateRandomPassword();
            userDto.setPassword(password);

            return userDto;
        }
        return null;
    }

    private void authenticateUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRang().getRangName()));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        authorities));
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
