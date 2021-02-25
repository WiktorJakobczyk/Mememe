package com.wjakobczyk.meme_me.service;

import com.wjakobczyk.meme_me.dto.UserDto;
import com.wjakobczyk.meme_me.exception.MemeMeException;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public UserDto getProfileInfo(){
        System.out.println("PROFILE");
        User user=userRepository.findByUsername(authService.getCurrentUser().getUsername())
                .orElseThrow(()->new MemeMeException("No user found!"));

        System.out.println(user);
        return new UserDto(user);
    }
}
