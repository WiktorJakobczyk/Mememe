package com.wjakobczyk.meme_me.exception;

public class MemeMeException extends RuntimeException {
    public MemeMeException(String exMessage, Exception e) {

        super(exMessage, e);
    }
    public MemeMeException(String exMessage) {

        super(exMessage);
    }
}
