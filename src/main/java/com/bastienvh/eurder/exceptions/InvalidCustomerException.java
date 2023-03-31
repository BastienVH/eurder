package com.bastienvh.eurder.exceptions;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String message) {
        super(message);
    }
}
