package com.wjakobczyk.meme_me.controller;
import com.wjakobczyk.meme_me.dto.CommentResponse;
import com.wjakobczyk.meme_me.security.JwtProvider;
import com.wjakobczyk.meme_me.service.CommentService;

import org.junit.jupiter.api.Test;
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

@WebMvcTest(controllers = CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;
    @MockBean
    private JwtProvider jwtProvider;
    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void testGetCommentsByPost() throws Exception {
        CommentResponse first = new CommentResponse(1L,3L,"User1","Test comment", Instant.now());
        CommentResponse second = new CommentResponse(1L,3L,"User1","Test comment", Instant.now());

        when(commentService.getAllCommentsForPost(anyLong())).thenReturn(Arrays.asList(first,second));

        mockMvc.perform(get("/api/comments/by-post/3"))
                .andExpect(status().isOk());
    }

}
