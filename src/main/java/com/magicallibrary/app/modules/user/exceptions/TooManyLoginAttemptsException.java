package com.magicallibrary.app.modules.user.exceptions;

public class TooManyLoginAttemptsException extends Exception {
    public TooManyLoginAttemptsException(String message) {
        super(message);
    }
}
