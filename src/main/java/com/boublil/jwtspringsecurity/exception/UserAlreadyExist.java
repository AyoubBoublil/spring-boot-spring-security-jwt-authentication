package com.boublil.jwtspringsecurity.exception;

public class UserAlreadyExist extends Exception {

    public UserAlreadyExist(String message) {
        super(message);
    }
}
