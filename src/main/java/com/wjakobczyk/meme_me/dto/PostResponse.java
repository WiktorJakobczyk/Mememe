package com.wjakobczyk.meme_me.dto;


import com.wjakobczyk.meme_me.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String postTitle;
    private String userName;
    private Integer voteCount;
    private String postCategory;
    private String imagePath;
    private Instant createDate;
   // private Integer commentCount;

    public PostResponse(Post post) {
        this.id=post.getId();
        this.postTitle=post.getTitle();
        this.userName=post.getUser().getUsername();
        this.voteCount=post.getVoteCount();
        this.postCategory=post.getCategory();
        this.imagePath=post.getImagePath();
        this.createDate=post.getCreateDate();
    }
}
