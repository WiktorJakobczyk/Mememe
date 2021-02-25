package com.wjakobczyk.meme_me.controller;

import com.wjakobczyk.meme_me.dto.PostResponse;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.security.JwtProvider;
import com.wjakobczyk.meme_me.service.AuthService;
import com.wjakobczyk.meme_me.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;


import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@AutoConfigureMockMvc
//@WebMvcTest
//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
    @MockBean
    private JwtProvider jwtProvider;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private AuthService authService;





    @Test
    public void testGetAllPosts() throws Exception {
        PostResponse first = new PostResponse(1L, "Title1", "User1",0,"Category","image1", Instant.now());
        PostResponse second = new PostResponse(2L, "Title2", "User1",0,"Category","image1", Instant.now());

        when(postService.getAllPosts()).thenReturn(Arrays.asList(first,second));

        mockMvc.perform(get("/api/posts")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                    .andExpect(content().string(containsString("\"id\":1,\"postTitle\":\"Title1\"")))
                    .andExpect(content().string(containsString("\"id\":2,\"postTitle\":\"Title2\"")));

    }

    @Test
    public void testGetPostById() throws Exception {
        PostResponse first = new PostResponse(1L, "Title1", "User1",0,"Category","image1", Instant.now());

        when(postService.getPostById(anyLong())).thenReturn(first);

        mockMvc.perform(get("/api/posts/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1,\"postTitle\":\"Title1\"")));

    }





}