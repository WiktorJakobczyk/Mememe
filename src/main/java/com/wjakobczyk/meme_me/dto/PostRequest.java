package com.wjakobczyk.meme_me.dto;


import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @NotBlank(message="Post Title must not be empty")
    private String postTitle;
    @Valid
    private User user;
    @NotBlank(message="Post category must not be empty")
    private String postCategory;


    public Post toPost() {
        Post result=new Post();
        result.setTitle(postTitle);
        result.setUser(user);
        result.setCreateDate(Instant.now());
        result.setVoteCount(0);
        result.setCategory(postCategory);
        return result;
    }
}
