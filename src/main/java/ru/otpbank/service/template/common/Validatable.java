package ru.otpbank.service.template.common;

import javax.validation.Validation;

public interface Validatable {

    default void validate() {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        if (validator != null) {
            validator.validate(this);
        }
    }
}
