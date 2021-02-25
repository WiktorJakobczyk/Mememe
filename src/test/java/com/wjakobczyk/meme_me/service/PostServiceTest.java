package com.wjakobczyk.meme_me.service;

import com.wjakobczyk.meme_me.dto.PostResponse;
import com.wjakobczyk.meme_me.exception.MemeMeException;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.repository.PostRepository;
import com.wjakobczyk.meme_me.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @Test
    @DisplayName("should throw UsernameNotFoundException when getAllPostsByUsername and username do not exists")
    void getAllPostsByUsername_And_usernameInvalid_throwsUsernameNotFoundException() {
        // given
        var mockRepository = mock(UserRepository.class);
        when(mockRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // system under test
        var toTest = new PostService(null,null,mockRepository,null,null);

        // when
        var exception = catchThrowable(() -> toTest.getAllPostsByUsername("UserTest"));

        // then
        assertThat(exception)
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("UserTest");
    }

    @Test
    @DisplayName("should throw MemeMeException when getPostById and id invalid")
    void getPostsById_And_idInvalid_throwsMemeMeException() {
        // given
        var mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        // system under test
        var toTest = new PostService(mockRepository,null,null,null,null);

        // when
        var exception = catchThrowable(() -> toTest.getPostById(3L));

        // then
        assertThat(exception)
                .isInstanceOf(MemeMeException.class)
                .hasMessageContaining("No post with given id");
    }

    @Test
    @DisplayName("should returns post")
    void getPostsById_And_idValid() {
        // given
        var mockRepository = mock(PostRepository.class);

        var testUser=new User(1L, "Test","Test","Test",Instant.now(),true,"USER");
        var first=new Post(1L, "Title1", "Category",0,Instant.now(), testUser, "image1" );
        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(first));

        // system under test
        var toTest = new PostService(mockRepository,null,null,null,null);

        // when
        var response = toTest.getPostById(1L);

        System.out.println(response);

        // then
        assertThat(response)
                .toString().contains("Title1");

    }

    @Test
    @DisplayName("should throw MemeMeException when deletePost and id invalid")
    void deletePost_And_idInvalid_throwsMemeMeException() {
        // given
        var mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        // system under test
        var toTest = new PostService(mockRepository,null,null,null,null);

        // when
        var exception = catchThrowable(() -> toTest.deletePost(3L));

        // then
        assertThat(exception)
                .isInstanceOf(MemeMeException.class)
                .hasMessageContaining("No post with given id");
    }


}
