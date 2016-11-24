package org.bionic.registration.listener;

import org.bionic.entity.User;
import org.bionic.registration.OnRegistrationCompleteEvent;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.StringJoiner;
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
        final User user = event.getUser();
//        final String token = UUID.randomUUID().toString();
//        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user);
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user) {
        final String recipientAddress = user.getEmail();
        final String subject = messages.getMessage("message.regSuccHeader", null, event.getLocale());
        final String message = createMessage(recipientAddress, user.getPassword(), event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(env.getRequiredProperty("support.email"));
        return email;
    }

    private String createMessage(String recipientAddress, String password, final Locale locale){
        final String message = messages.getMessage("message.regSucc", null, locale)+"\r\n"
                +messages.getMessage("message.loginName", null, locale)+": "
                +recipientAddress+"\n"
                +messages.getMessage("message.passwordName", null, locale)+": "
                +password+"\n";
        return message;
    }
}
