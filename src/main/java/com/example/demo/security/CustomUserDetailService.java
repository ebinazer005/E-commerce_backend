package com.example.demo.security;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Component; 
import com.example.demo.UserEntity; 
import com.example.demo.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        return new User(user.getEmail() ,user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())));
    }
}
