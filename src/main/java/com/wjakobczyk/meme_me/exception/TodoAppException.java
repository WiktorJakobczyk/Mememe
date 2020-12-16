package com.wjakobczyk.meme_me.exception;

public class TodoAppException extends RuntimeException {
    public TodoAppException(String exMessage, Exception e) {

        super(exMessage, e);
    }
    public TodoAppException(String exMessage) {

        super(exMessage);
    }
}
