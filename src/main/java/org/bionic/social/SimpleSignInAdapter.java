package org.bionic.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by Alex Sanak on 26.11.2016.
 */
public class SimpleSignInAdapter implements SignInAdapter {
    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        return null;
    }
}
