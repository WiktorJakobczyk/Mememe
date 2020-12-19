package com.wjakobczyk.meme_me.controller;


import com.wjakobczyk.meme_me.dto.CommentRequest;
import com.wjakobczyk.meme_me.dto.CommentResponse;
import com.wjakobczyk.meme_me.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest toCreate) {
        CommentResponse result = commentService.createComment(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        // return new ResponseEntity<>(CREATED);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<CommentResponse> editComment(@PathVariable Long id, @RequestBody CommentRequest toEdit){
        commentService.editComment(id,toEdit);
        return ResponseEntity.noContent().build();
    }
}
