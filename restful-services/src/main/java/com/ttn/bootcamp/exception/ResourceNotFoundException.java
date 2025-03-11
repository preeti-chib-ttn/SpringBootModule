package com.ttn.bootcamp.exception;


// Day 1 - Question 6
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}