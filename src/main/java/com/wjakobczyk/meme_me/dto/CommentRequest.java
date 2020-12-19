package com.wjakobczyk.meme_me.dto;


import com.wjakobczyk.meme_me.model.Comment;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
   // private Long id;
    private Long postId;
   // private Instant createdDate;
    private String text;
    private User user;

    public Comment toComment(Post post) {
        Comment result=new Comment();
        result.setPost(post);
        result.setText(this.text);
        result.setUser(this.user);
        result.setCreatedDate(Instant.now());
        return result;
    }

}
