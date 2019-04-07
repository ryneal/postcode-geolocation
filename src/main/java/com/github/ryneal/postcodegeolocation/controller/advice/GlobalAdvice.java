package com.github.ryneal.postcodegeolocation.controller.advice;

import com.github.ryneal.postcodegeolocation.controller.entity.ErrorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Component
public class GlobalAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAdvice.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handle(MethodArgumentNotValidException exception) {
        LOGGER.info(exception.getMessage());
        return new ErrorEntity(exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("A value provided was not valid"));
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handle(ConstraintViolationException exception) {
        LOGGER.info(exception.getMessage());
        return new ErrorEntity(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("A value provided was not valid"));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorEntity handle(Exception e) {
        LOGGER.error("Unknown error occurred", e);
        return new ErrorEntity("Internal error occurred. Please contact the system administrator");
    }
}
