package com.wjakobczyk.meme_me.service;


import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> userOptional = userRepository.findByUsername(username);
         User user = userOptional
                 .orElseThrow(()-> new UsernameNotFoundException("No user found with username :"+username));

         return new org.springframework.security
                 .core
                 .userdetails.
                 User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true,true, getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
