package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.peresvit.dto.UserDto;
import ua.peresvit.entity.User;
import ua.peresvit.error.UserAlreadyExistException;
import ua.peresvit.service.UserService;
import ua.peresvit.service.registration.OnRegistrationCompleteEvent;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    private final ConnectionRepository connectionRepository;

    private final MessageSource messages;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher, ConnectionRepository connectionRepository, MessageSource messages) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.connectionRepository = connectionRepository;
        this.messages = messages;
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
            @ModelAttribute("user") UserDto accountDto,
            final HttpServletRequest request,
            Model model) {

        User registered = createUserAccount(accountDto);
        if (registered == null) {
            model.addAttribute("message", messages.getMessage("message.regError", null, request.getLocale()));
            return "home";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request), true));
        model.addAttribute("message", messages.getMessage("message.regConfirm", null, request.getLocale()));
        return "redirect:/";
    }

//    validate token
    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
//            Here info about successful verification added to message attribute)
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            authenticateUser(user);
            return "redirect:/home/workField";
        }
//        Show unsuccessful operation on the main page
//        TODO add attribute message on the main and personal pages
        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
//        model.addAttribute("expired", "expired".equals(result));
//        model.addAttribute("token", token);
        return "home";
    }

//    Register user from social networks
    @RequestMapping(value = "/social", method = RequestMethod.POST)
    public String registerUserAccountFromSN(
            @ModelAttribute("user") UserDto accountDto, final HttpServletRequest request) {

        User registered = createUserAccount(accountDto);
        if (registered == null) {
            return "registration/registration";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request), false));
        authenticateUser(registered);
        return "redirect:/home/workField";
    }

//    Facebook registration
    @RequestMapping(value="/facebook", method=RequestMethod.GET)
    public String facebook(final HttpServletRequest request) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return "redirect:/connect/facebook";
        }
        UserDto userDtoFB = createSocialUserDtoForFB(connection);
        User user = userService.findUserByEmail(userDtoFB.getEmail());
        if (user != null) {
            authenticateUser(userService.findUserByEmail(userDtoFB.getEmail()));
            return "redirect:/home/workField";
        }
        return "redirect:/registration/facebook/finishRegistration";
    }

//    Redirect page after FB getting access to fulfill User's info
    @RequestMapping(value="/facebook/finishRegistration", method=RequestMethod.GET)
    public String facebookFinish(Model model) {
        try {
            Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
            UserDto userDto = createSocialUserDtoForFB(connection);
            model.addAttribute("user", userDto);
            return "registration/registration";
        } catch (NullPointerException ex) {
            return "redirect:/connect/facebook";
        }
    }


//    Google registration
    @RequestMapping(value="/google", method=RequestMethod.GET)
    public String googlePlus(final HttpServletRequest request) {
        Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
        if (connection == null) {
            return "redirect:/connect/google";
        }
        UserDto socialUserDtoForGoogle = createSocialUserDtoForGoogle(connection);
        User user = userService.findUserByEmail(socialUserDtoForGoogle.getEmail());
        if (user != null) {
            authenticateUser(userService.findUserByEmail(socialUserDtoForGoogle.getEmail()));
            return "redirect:/home/workField";
        }
        return "redirect:/registration/google/finishRegistration";
    }

//    Redirect page after Google getting access to fulfill User's info
    @RequestMapping(value="/google/finishRegistration", method=RequestMethod.GET)
    public String finishGoogleRegistration(Model model) {
        try {
            Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
            UserDto userDto = createSocialUserDtoForGoogle(connection);
            model.addAttribute("user", userDto);
            return "registration/registration";
        } catch (NullPointerException ex) {
            return "redirect:/connect/google";
        }
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

//    VK registration with redirect to registration page to finish registration
    @RequestMapping(value="/vkontakte", method=RequestMethod.GET)
    public String vkontakte() {
        Connection<VKontakte> connection = connectionRepository.findPrimaryConnection(VKontakte.class);
        if (connection == null) {
            return "redirect:/connect/vkontakte";
        }
        return "redirect:/registration/vkontakte/finishRegistration";
    }

//    Redirect page after VK getting access to fulfill User's info
    @RequestMapping(value = "/vkontakte/finishRegistration", method = RequestMethod.GET)
    public String finishVkRegistration(Model model) {
        try {
            Connection<VKontakte> connection = connectionRepository.findPrimaryConnection(VKontakte.class);
            UserDto userDto = createSocialUserDtoForVK(connection);
            model.addAttribute("user", userDto);
            return "registration/registration";
        } catch (NullPointerException ex) {
            return "redirect:/connect/vkontakte";
        }
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
        Facebook facebook = connection.getApi();
//            Here we can get fields from FB connection
        String [] fields = { "id", "email",  "first_name", "last_name" , };
        org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        UserDto userDto = new UserDto();
        userDto.setEmail(userProfile.getEmail());
        userDto.setFirstName(userProfile.getFirstName());
        userDto.setLastName(userProfile.getLastName());
        userDto.setProfileFB(userProfile.getLink());

        return userDto;
    }

//Creation DTO from connection with VK
    private UserDto createSocialUserDtoForVK(Connection<VKontakte> connection) {
        VKontakte vKontakte = connection.getApi();
//            Here we can get fields from VK connection
        VKontakteProfile userProfile = vKontakte.usersOperations().getProfile();
        UserDto userDto = new UserDto();
//            VK user has no email
//        userDto.setEmail("");
        userDto.setFirstName(userProfile.getFirstName());
        userDto.setLastName(userProfile.getLastName());
        userDto.setProfileVK(userProfile.getProfileURL());
//        userDto.setPassword("");
//        userDto.setMatchingPassword("");
        return userDto;
    }

//Creation DTO from connection with Google
    private UserDto createSocialUserDtoForGoogle(Connection<Google> connection) {
        Google google = connection.getApi();
//            Here we can get fields from FB connection
//        String [] fields = { "id", "email",  "first_name", "last_name" , };
//        org.springframework.social.facebook.api.User userProfile = google.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        Person googleProfile = google.plusOperations().getGoogleProfile();
        UserDto userDto = new UserDto();
        userDto.setEmail(googleProfile.getAccountEmail());
        userDto.setFirstName(googleProfile.getGivenName());
        userDto.setLastName(googleProfile.getFamilyName());
        userDto.setProfileGoogle(googleProfile.getUrl());

        return userDto;
    }

    private void authenticateUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
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