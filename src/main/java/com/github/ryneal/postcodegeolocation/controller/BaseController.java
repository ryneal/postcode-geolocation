package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.controller.entity.ErrorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
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


    @ExceptionHandler({ConstraintViolationException.class})
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

    @ExceptionHandler({NumberFormatException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handle(NumberFormatException exception) {
        LOGGER.info(exception.getMessage());
        return new ErrorEntity("A non-numeric value was provided, " + exception.getMessage());
    }
}
