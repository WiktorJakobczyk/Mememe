package com.wjakobczyk.meme_me.service;


import com.wjakobczyk.meme_me.dto.VoteDto;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.Vote;
import com.wjakobczyk.meme_me.repository.PostRepository;
import com.wjakobczyk.meme_me.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public void vote(Long id, VoteDto voteDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post Not Found with ID "));
        Optional<Vote> voteByPostAndUser = voteRepository.findVoteByPostAndUser(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent()){
            //throw new IllegalStateException("You have already voted");
            if(voteByPostAndUser.get().isVotedUp()) {
                if (voteDto.isUp())
                    throw new IllegalStateException("You have already voted");
                else
                    voteDownNow(voteByPostAndUser.get(),post);
            }
            else{
                if(voteDto.isUp())
                    voteUpNow(voteByPostAndUser.get(), post);
                else
                    throw new IllegalStateException("You have already voted");
            }


        }
        else
            voteFirstTime(voteDto,post);



    }

    private void voteDownNow(Vote vote, Post post){
        post.setVoteCount(post.getVoteCount()-2);
        vote.setVotedUp(false);
        vote.setVotedDown(true);
        postRepository.save(post);
        voteRepository.save(vote);
    }

    private void voteUpNow(Vote vote, Post post){
        post.setVoteCount(post.getVoteCount()+2);
        vote.setVotedUp(true);
        vote.setVotedDown(false);
        voteRepository.save(vote);
        postRepository.save(post);
    }

    private void voteFirstTime(VoteDto voteDto, Post post){
        if(voteDto.isUp()){
            post.setVoteCount(post.getVoteCount()+1);
        }
        else
            post.setVoteCount(post.getVoteCount()-1);

        save(post,voteDto.isUp());
    }

    private void save(Post post, boolean check){
        Vote vote= new Vote();
        vote.setPost(post);
        vote.setUser(authService.getCurrentUser());
        if(check) {
            vote.setVotedUp(true);
            vote.setVotedDown(false);
        }
        else{
            vote.setVotedUp(false);
            vote.setVotedDown(true);
        }
        voteRepository.save(vote);
        postRepository.save(post);
    }
}
