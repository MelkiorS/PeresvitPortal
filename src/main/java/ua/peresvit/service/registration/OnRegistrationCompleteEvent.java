package ua.peresvit.service.registration;


import org.springframework.context.ApplicationEvent;
import ua.peresvit.entity.User;

import java.util.Locale;

@SuppressWarnings("serial")

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;
    private final boolean isWithToken;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl, boolean isWithToken) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.isWithToken = isWithToken;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }

    public boolean getIsWithToken() {
        return isWithToken;
    }
}
