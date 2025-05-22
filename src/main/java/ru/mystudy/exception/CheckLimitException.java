package ru.mystudy.exception;

public class CheckLimitException extends RuntimeException {
    public CheckLimitException(String message) {
        super(message);
    }
}
