package com.wjakobczyk.meme_me.dto;


import com.wjakobczyk.meme_me.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private Long postId;
    private String userName;
    private String text;
    private Instant createdOn;
   // private Integer voteCount;
    // private Integer commentCount;

    public CommentResponse(Comment comment) {
        this.id=comment.getId();
        this.postId=comment.getPost().getId();
        this.userName=comment.getUser().getUsername();
        this.text=comment.getText();
        this.createdOn=comment.getCreatedDate();
    }

}
