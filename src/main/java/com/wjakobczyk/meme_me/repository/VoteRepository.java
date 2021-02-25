package com.wjakobczyk.meme_me.repository;


import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findVoteByPostAndUser(Post post, User user);
    Vote save(Vote entity);
    List<Vote> findAllByPost(Post post);
}
