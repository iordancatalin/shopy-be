package com.shopy.shopybe.exception.model;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException() {
        super("Email address already exists");
    }
}
