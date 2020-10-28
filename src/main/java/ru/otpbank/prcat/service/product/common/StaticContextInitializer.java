package ru.otpbank.prcat.service.product.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

@Component
@RequiredArgsConstructor
public class StaticContextInitializer {

    private final Validator validator;

    @PostConstruct
    public void init() {
        ValidationUtils.setValidator(validator);
    }
}
