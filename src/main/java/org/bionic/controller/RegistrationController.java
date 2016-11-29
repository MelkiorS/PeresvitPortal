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
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Sanak on 10.11.2016.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    private final ConnectionRepository connectionRepository;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher, ConnectionRepository connectionRepository) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.connectionRepository = connectionRepository;
    }

    // Show registration form
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
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

//    FaceBook registration
    @RequestMapping(value="/facebook", method=RequestMethod.GET)
    public String facebook(final HttpServletRequest request) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return "redirect:/connect/facebook";
        }
        UserDto userDtoFB = createSocialUserDtoForFB(connection);
        User user = createUserAccount(userDtoFB);
        if (user == null) {
            authenticateUser(userService.findUserByEmail(userDtoFB.getEmail()));
            return "redirect:/home/workField";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), getAppUrl(request)));
        authenticateUser(user);
        return "redirect:/home/workField";
    }


    //    VK registration
    @RequestMapping(value="/vkontakte", method=RequestMethod.GET)
    public String vkontakte() {
        Connection<VKontakte> connection = connectionRepository.findPrimaryConnection(VKontakte.class);
        if (connection == null) {
            return "redirect:/connect/vkontakte";
        }
        return "redirect:/registration/finishRegistration";
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
//

//    Redirect page after VK getting access to fulfill User's info
    @RequestMapping(value = "/finishRegistration", method = RequestMethod.GET)
    public String finishVkRegistration(Model model) {
        Connection<VKontakte> connection = connectionRepository.findPrimaryConnection(VKontakte.class);
        UserDto userDto = createSocialUserDtoForVK(connection);
        model.addAttribute("user", userDto);
        return "registration/registration";
    }

//    -----------------------------------------------------------------------------------------------------------------

    private User createUserAccount(UserDto accountDto) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }

//Creation DTO from connection with FB
    private UserDto createSocialUserDtoForFB(Connection<Facebook> connection) {
        if(connection != null) {
            Facebook facebook = connection.getApi();
//            Here we can get fields from FB connection
            String [] fields = { "id", "email",  "first_name", "last_name" , };
            org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
            UserDto userDto = new UserDto();
            userDto.setEmail(userProfile.getEmail());
            userDto.setFirstName(userProfile.getFirstName());
            userDto.setLastName(userProfile.getLastName());
            String password = PasswordGenerator.generateRandomPassword();
            userDto.setPassword(password);

            return userDto;
        }
        return null;
    }

//Creation DTO from connection with VK
    private UserDto createSocialUserDtoForVK(Connection<VKontakte> connection) {
        if(connection != null) {
            VKontakte vKontakte = connection.getApi();
//            Here we can get fields from VK connection
            VKontakteProfile userProfile = vKontakte.usersOperations().getProfile();
            UserDto userDto = new UserDto();
//            VK user has no email
//            userDto.setEmail(userProfile.getEmail());
            userDto.setFirstName(userProfile.getFirstName());
            userDto.setLastName(userProfile.getLastName());
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
//Fields available for FB
//{ "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work"}