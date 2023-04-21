package com.bastienvh.eurder.exceptions;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message) {
        super(message);
    }
}
