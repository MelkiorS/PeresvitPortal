package ua.peresvit.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.UserRepository;
import ua.peresvit.entity.User;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (!user.isEnabled()) {
                throw new RuntimeException("User is unable.");
            }
            List<GrantedAuthority> authorities =
                    new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getRoleName()));
            ExampleUserDetails principal = ExampleUserDetails.getBuilder()
                    .firstName(user.getFirstName())
                    .id(user.getUserId())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .socialSignInProvider(user.getSocialMediaServices())
                    .username(user.getEmail())
                    .build();
            return principal;
        }
        throw new UsernameNotFoundException("User with email '" + email + "' not found.");
    }
}