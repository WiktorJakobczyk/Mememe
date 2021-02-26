package com.wjakobczyk.meme_me.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {
    @Test
    @DisplayName("Test @NotBlank title")
    void testPostTitleEmpty() {
        var post = new Post(1L,"","Category",0, Instant.now(),null,null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size()).isEqualTo(1);;
    }

    @Test
    @DisplayName("Test @NotBlank title when null")
    void testPostTitleNull() {
        var post = new Post(1L,null,"Category",0, Instant.now(),null,null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size()).isEqualTo(1);;
    }

    @Test
    @DisplayName("Test @NotBlank category")
    void testPostCategoryEmpty() {
        var post = new Post(1L,"Tile1","",0, Instant.now(),null,null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size()).isEqualTo(1);;
    }

    @Test
    @DisplayName("Test @NotBlank title when null")
    void testPostCategoryNull() {
        var post = new Post(1L,"Title1",null,0, Instant.now(),null,null);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertThat(violations.size()).isEqualTo(1);;
    }
}
