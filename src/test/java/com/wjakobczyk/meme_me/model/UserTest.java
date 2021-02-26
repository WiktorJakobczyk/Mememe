package com.wjakobczyk.meme_me.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("Test @NotBlank Username empty" )
    void testUserUsernameEmpty() {
        var user = new User(1L,"","Test","TestEmail@email.com", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);

        
    }
    @Test
    @DisplayName("Test @NotBlank Username null" )
    void testUserUsernameNull() {
        var user = new User(1L,null,"Test","TestEmail@email.com", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test @NotBlank Password empty" )
    void testUserPasswordEmpty() {
        var user = new User(1L,"Username","","TestEmail@email.com", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }
    @Test
    @DisplayName("Test @NotBlank Password null" )
    void testUserPasswordNull() {
        var user = new User(1L,"Username",null,"TestEmail@email.com", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("Test @NotBlank Email invalid" )
    void testUserEmailInvalid() {
        var user = new User(1L,"Username","Password","TestEmail", Instant.now(), true,"User");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }
}
