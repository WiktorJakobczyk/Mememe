package com.wjakobczyk.meme_me.service;


import com.wjakobczyk.meme_me.dto.CommentRequest;
import com.wjakobczyk.meme_me.dto.CommentResponse;
import com.wjakobczyk.meme_me.model.Comment;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.repository.CommentRepository;
import com.wjakobczyk.meme_me.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public List<CommentResponse> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(CommentResponse::new).collect(toList());
    }

    public CommentResponse createComment(CommentRequest toCreate) {
        toCreate.setUser(authService.getCurrentUser());
        Post post = postRepository.findById(toCreate.getPostId())
                .orElseThrow(() -> new IllegalArgumentException(toCreate.getPostId().toString()));

        Comment result=commentRepository.save(toCreate.toComment(post));
        //commentRepository.save(result);
        return new CommentResponse(result);

    }

    public void editComment(Long id, CommentRequest toEdit) {
        if(!commentRepository.existsById(id)){
            return;
        }
        commentRepository.findById(id)
                .ifPresent(comment->{
                    comment.setText(toEdit.getText());
                    commentRepository.save(comment);
                });
    }
}
