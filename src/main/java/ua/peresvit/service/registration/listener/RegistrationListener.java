package ua.peresvit.service.registration.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ua.peresvit.entity.User;
import ua.peresvit.service.UserService;
import ua.peresvit.service.registration.OnRegistrationCompleteEvent;

import java.util.Locale;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        if (!event.getIsDone()) {
            final User user = event.getUser();
            SimpleMailMessage email;
            if (event.getIsWithToken()) {
                final String token = UUID.randomUUID().toString();
                service.createVerificationTokenForUser(user, token);
                email = constructEmailMessage(event, user, token);
            } else {
                email = constructEmailMessage(event, user, null);
            }
            mailSender.send(email);
            event.setIsDone(true);
        }
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, String token) {
        final String recipientAddress = user.getEmail();
        final String subject = messages.getMessage("message.regSuccHeader", null, event.getLocale());
        String message;
        if (token != null){
            message = createMessageWithToken(event, recipientAddress, event.getLocale(), token);
        } else {
            message = createMessage(recipientAddress, event.getLocale());
        }
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(env.getRequiredProperty("support.email"));
        return email;
    }

    private String createMessage(String recipientAddress, final Locale locale){
        final String message = messages.getMessage("message.regSucc", null, locale)+" "
                +recipientAddress;
        return message;
    }

    private String createMessageWithToken(final OnRegistrationCompleteEvent event, String recipientAddress, final Locale locale, String token){
        final String message = messages.getMessage("message.regSucc", null, locale)+" "
                +recipientAddress+"\n"
                +messages.getMessage("token.message", null, locale)+" " +event.getAppUrl() + "/registration/registrationConfirm?token="
                +token;
        return message;
    }
}
