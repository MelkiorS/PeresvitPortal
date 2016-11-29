package org.bionic.social;

import lombok.Data;
import org.bionic.entity.Rang;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex Sanak on 25.11.2016.
 */
@Data
public class SocialUserDetails extends SocialUser {

    private Long id;

    private String firstName;

    private String lastName;

    private Rang rang;

    private SocialMediaService socialSignInProvider;

    public SocialUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    //Getters are omitted for the sake of clarity.

    public static class Builder {

        private Long id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private Rang rang;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder rang(Rang rang) {
            this.rang = rang;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rang.getRangName());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public SocialUserDetails build() {
            SocialUserDetails user = new SocialUserDetails(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.rang = rang;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}
