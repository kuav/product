package ru.otpbank.prcat.service.product.common;

import javax.validation.ConstraintViolationException;

public interface Validatable {

    default void validate() {
        var validator = ValidationUtils.getValidator();
        if (validator != null) {
            var violations = validator.validate(this);
            if (violations != null && !violations.isEmpty())
                throw new ConstraintViolationException(violations);
        }
    }
}
