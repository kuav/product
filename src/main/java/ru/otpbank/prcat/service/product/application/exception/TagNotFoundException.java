package ru.otpbank.prcat.service.product.application.exception;

import ru.otpbank.prcat.service.product.common.exception.NotFoundException;

public class TagNotFoundException extends NotFoundException {

    public TagNotFoundException(String id) {
        super("Could not find tag " + id);
    }
}
