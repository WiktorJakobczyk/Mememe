package com.wjakobczyk.meme_me.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Post title cannot be empty or Null")
    private String title;
    @NotBlank(message = "Post category cannot be empty or Null")
    private String category;
    private Integer voteCount;
    private Instant createDate;
    @ManyToOne(fetch = EAGER)   // why LAZY not working?
    private User user;

    private String imagePath;


    // TEST ONLY
    public Post(Post post) {
        this.id=post.id;
        this.title=post.title;
        this.voteCount=post.voteCount;
       // this.user=post.user;
        this.createDate=post.createDate;
    }
}
