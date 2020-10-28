package ru.otpbank.prcat.service.product.application.exception;

import ru.otpbank.prcat.service.product.common.exception.UniqueViolationException;

public class TagUniqueViolationException extends UniqueViolationException {

    public TagUniqueViolationException(String fullName) {
        super("Tag with same fullName is already exists: " + fullName);
    }
}
