package ru.otpbank.prcat.service.product.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otpbank.prcat.service.product.domain.model.CreateTagCmd;
import ru.otpbank.prcat.service.product.domain.model.UpdateTagCmd;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@DisplayName("Тест на валидация тегов")
class TagValidationTest {
    @Test
    @DisplayName("Валидация аттрибутов создания тэга")
    void testCreateFullNameNotNull() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> CreateTagCmd.builder().build());
        Assertions.assertThat(exception.getConstraintViolations())
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("Атрибут name сущности Tag должен быть заполнен");
    }

    @Test
    @DisplayName("Валидация аттрибутов обновления тега")
    void testUpdateFullNameAndIdNotNull() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> UpdateTagCmd.builder().build());
        Assertions.assertThat(exception.getConstraintViolations())
                .hasSize(2)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("Атрибут name сущности Tag должен быть заполнен",
                        "Атрибут id сущности Tag должен быть заполнен");
    }
}
