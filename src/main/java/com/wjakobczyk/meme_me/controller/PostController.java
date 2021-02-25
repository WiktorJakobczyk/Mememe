package com.wjakobczyk.meme_me.controller;



import com.wjakobczyk.meme_me.dto.PostRequest;
import com.wjakobczyk.meme_me.dto.PostResponse;
import com.wjakobczyk.meme_me.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @ResponseBody
    @GetMapping
    ResponseEntity<List<PostResponse>> readAllPosts() {

        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    ResponseEntity<PostResponse> readPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest toCreate) {
        PostResponse result = postService.createPost(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/by-user/{username}")
    ResponseEntity<List<PostResponse>> readAllPostsByUsername(@PathVariable String username) {
        System.out.println("USERNAME: "+username);
        return ResponseEntity.ok(postService.getAllPostsByUsername(username));
    }

    @GetMapping("/by-category/{category}")
    ResponseEntity<List<PostResponse>> readAllPostsByCategory(@PathVariable String category){
        return ResponseEntity.ok(postService.getAllPostsByCategory(category));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePost(@PathVariable Long id){

        return ResponseEntity.ok(postService.deletePost(id));
    }
}
