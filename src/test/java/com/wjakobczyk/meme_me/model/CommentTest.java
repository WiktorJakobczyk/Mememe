package com.wjakobczyk.meme_me.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {
    @Test
    @DisplayName("Test @NotEmpty")
    void testCommentTitleEmpty() {
        var post = new Post(1L,"Title","Category",0, Instant.now(),null,null);
        var user = new User(1L,"Test","Test","TestEmail", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(new Comment(1L,"",post,Instant.now(),user));

        assertThat(violations.size()).isEqualTo(1);;
    }
}
