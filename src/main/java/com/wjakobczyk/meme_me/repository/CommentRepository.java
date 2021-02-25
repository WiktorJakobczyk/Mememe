package com.wjakobczyk.meme_me.repository;


import com.wjakobczyk.meme_me.model.Comment;
import com.wjakobczyk.meme_me.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByPost(Post post);

}