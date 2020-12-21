package com.wjakobczyk.meme_me.repository;


import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    Post save(Post entity);
    List<Post> findAllByUser(User username);
    List<Post> findAllByCategory(String category);

}