package ua.peresvit.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;
import ua.peresvit.entity.Role;
import ua.peresvit.util.helper.SocialMediaService;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExampleUserDetails extends SocialUser {
    private Long id;

    private String firstName;

    private String lastName;

    private Role role;

    private List<SocialMediaService> socialSignInProvider;

    public ExampleUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    //Getters are omitted for the sake of clarity.

    public static Builder getBuilder(){
        return new Builder();
    }
    @Data
    public static class Builder {

        private Long id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private Role role;

        private List<SocialMediaService> socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
            this.socialSignInProvider = new ArrayList<>();
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

        public Builder role(Role role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(List<SocialMediaService> socialSignInProvider) {
            if (!socialSignInProvider.isEmpty()) this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public ExampleUserDetails build() {
            ExampleUserDetails user = new ExampleUserDetails(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}
