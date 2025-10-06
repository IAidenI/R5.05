package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("L'utilisateur '" + username + "' existe déjà.");
    }
}