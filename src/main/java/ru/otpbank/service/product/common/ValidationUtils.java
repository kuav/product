package ru.otpbank.service.product.common;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtils {

    private static Validator validator;

    private ValidationUtils() {
    }

    public static Validator getValidator() {
        return validator != null ? validator : Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void setValidator(Validator validator) {
        ValidationUtils.validator = validator;
    }
}
