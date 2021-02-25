//package com.wjakobczyk.meme_me;
//
//
//
//import com.wjakobczyk.meme_me.service.AuthService;
//import com.wjakobczyk.meme_me.service.PostService;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ResourceBundleMessageSource;
//
//
//@Configuration
//@ComponentScan(basePackages = {"com.wjakobczyk.meme_me"})
//public class TestConfiguration {
//
//
//
//    @Bean
//    public PostService postService() {
//        return Mockito.mock(PostService.class);
//    }
//
//    @Bean
//    AuthService authService(){
//        return Mockito.mock(AuthService.class);
//    }
//}