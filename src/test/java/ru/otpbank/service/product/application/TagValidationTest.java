package ru.otpbank.service.product.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otpbank.service.product.domain.model.CreateTagCmd;
import ru.otpbank.service.product.domain.model.UpdateTagCmd;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagValidationTest {
    @Test
    public void testCreateFullNameNotNull() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> CreateTagCmd.builder().build());
        Assertions.assertThat(exception.getConstraintViolations())
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("Атрибут fullName сущности Tag должен быть заполнен");
    }

    @Test
    public void testUpdateFullNameAndIdNotNull() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> UpdateTagCmd.builder().build());
        Assertions.assertThat(exception.getConstraintViolations())
                .hasSize(2)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("Атрибут fullName сущности Tag должен быть заполнен",
                        "Атрибут id сущности Tag должен быть заполнен");
    }
}
