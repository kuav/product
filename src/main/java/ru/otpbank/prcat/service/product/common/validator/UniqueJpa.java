package ru.otpbank.prcat.service.product.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE, ANNOTATION_TYPE, METHOD, CONSTRUCTOR})
@Constraint(validatedBy = UniqueJpaValidator.class)
@Retention(RUNTIME)
public @interface UniqueJpa {
    String message();

    String[] fields();

    Class<?> entityClass();

    String[] entityFields();

    String idField() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
