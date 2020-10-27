package ru.otpbank.service.product.application.exception;

public class TagUniqueViolationException extends RuntimeException {

    public TagUniqueViolationException(String fullName) {
        super("Tag with same fullName is already exists: " + fullName);
    }
}
