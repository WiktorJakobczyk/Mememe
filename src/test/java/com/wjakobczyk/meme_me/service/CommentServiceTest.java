package com.wjakobczyk.meme_me.service;

import com.wjakobczyk.meme_me.dto.CommentRequest;
import com.wjakobczyk.meme_me.model.Comment;
import com.wjakobczyk.meme_me.model.Post;
import com.wjakobczyk.meme_me.model.User;
import com.wjakobczyk.meme_me.repository.CommentRepository;
import com.wjakobczyk.meme_me.repository.PostRepository;
import com.wjakobczyk.meme_me.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    private static Validator validator;


    @Test
    @DisplayName("should throw IllegalArgumentException when getAllCommentsForPost and postId invalid")
    void getAllCommentsForPost_andIdInvalid_throwsIllegalArgumentException() {
        // given
        var mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        // system under test
        var toTest = new CommentService(mockRepository,null,null);

        // when
        var exception = catchThrowable(() -> toTest.getAllCommentsForPost(3L));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when editComment and id invalid")
    void editComment_andIdInvalid_throwsIllegalArgumentException() {
        // given
        var mockRepository = mock(CommentRepository.class);
        when(mockRepository.existsById(anyLong())).thenReturn(false);

        // system under test
        var toTest = new CommentService(null,mockRepository,null);

        // when
        var exception = catchThrowable(() -> toTest.editComment(3L, null));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when editComment and id invalid")
    void deleteComment_andIdInvalid_throwsIllegalArgumentException() {
        // given
        var mockRepository = mock(CommentRepository.class);
        when(mockRepository.existsById(anyLong())).thenReturn(false);

        // system under test
        var toTest = new CommentService(null,mockRepository,null);

        // when
        var exception = catchThrowable(() -> toTest.deleteComment(3L));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when addComment and post do not exists")
    void addComment_andIdInvalid_throwsIllegalArgumentException() {
        // given
        var mockRepository = mock(PostRepository.class);
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        var mockService = mock(AuthService.class);
        when(mockService.getCurrentUser()).thenReturn(new User(1L,"Test","Test","TestEmail", Instant.now(), true,"User"));

        // system under test
        var toTest = new CommentService(mockRepository,null,mockService);

        // when
        var exception = catchThrowable(() -> toTest.createComment(new CommentRequest(1L,"TEST",null)));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1");
    }

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }




}
