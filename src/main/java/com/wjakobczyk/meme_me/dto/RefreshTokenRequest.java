package com.wjakobczyk.meme_me.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
    private String username;

}
