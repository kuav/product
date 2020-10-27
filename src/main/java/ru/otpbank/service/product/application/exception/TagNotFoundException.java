package ru.otpbank.service.product.application.exception;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(String id) {
        super("Could not find tag " + id);
    }
}
