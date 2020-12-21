package com.wjakobczyk.meme_me.service;


import com.wjakobczyk.meme_me.dto.PostRequest;
import com.wjakobczyk.meme_me.dto.PostResponse;
import com.wjakobczyk.meme_me.exception.MemeMeException;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.repository.PostRepository;
import com.wjakobczyk.meme_me.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());

    }




    public PostResponse createPost(PostRequest toCreate) {
        toCreate.setUser(authService.getCurrentUser());
        Post result=postRepository.save(toCreate.toPost());
        result.setImagePath("/images/memes/"+result.getCategory()+"/test"+result.getId()+".jpg");



        return new PostResponse(result);
    }

    public  List<PostResponse> getAllPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findAllByUser(user).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public  List<PostResponse>  getAllPostsByCategory(String category){
        return postRepository.findAllByCategory(category).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id){
        return postRepository.findById(id).map(PostResponse::new)
                .orElseThrow(() -> new MemeMeException("No post with given id"));
    }
}
