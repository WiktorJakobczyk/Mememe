package com.wjakobczyk.meme_me.dto;


import com.wjakobczyk.meme_me.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private Instant created;

    public UserDto(User user) {
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.created=user.getCreated();
    }
}
