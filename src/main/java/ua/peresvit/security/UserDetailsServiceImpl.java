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
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            if (!user.isEnabled()) {
                throw new RuntimeException("User is unable.");
            }
            return ExampleUserDetails.getBuilder()
                    .firstName(user.getFirstName())
                    .id(user.getUserId())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .socialSignInProvider(user.getSocialMediaServices())
                    .username(user.getEmail())
                    .build();
        }
        throw new UsernameNotFoundException("User with email '" + username + "' not found.");
    }
}