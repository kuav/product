package ru.otpbank.prcat.service.product.common.exception;

public class UniqueViolationException extends RuntimeException {

    public UniqueViolationException(String message) {
        super(message);
    }
}
