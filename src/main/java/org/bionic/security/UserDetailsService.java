package org.bionic.security;

import org.bionic.dao.UserRepository;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Sanak on 12.11.2016.
 */
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        org.bionic.entity.User user = userRepository.findByEmail(username);
        if (user != null) {
            List<GrantedAuthority> authorities =
                    new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities);
        }
        throw new UsernameNotFoundException(
                "User with email '" + username + "' not found.");
    }
}