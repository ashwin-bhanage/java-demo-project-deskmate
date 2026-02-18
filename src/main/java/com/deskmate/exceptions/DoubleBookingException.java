package com.deskmate.exceptions;

public class DoubleBookingException extends RuntimeException {

    public DoubleBookingException(String message) {
        super(message);
    }
}
