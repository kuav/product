package ru.otpbank.prcat.service.product.adapter.in.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otpbank.prcat.service.product.common.exception.NotFoundException;
import ru.otpbank.prcat.service.product.common.exception.UniqueViolationException;
import ru.otpbank.prcat.service.product.common.exception.ValidatorInitializingException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "))
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueViolationException.class)
    ResponseEntity<String> handleUniqueViolationException(UniqueViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidatorInitializingException.class)
    ResponseEntity<String> handleValidatorInitilazingException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
