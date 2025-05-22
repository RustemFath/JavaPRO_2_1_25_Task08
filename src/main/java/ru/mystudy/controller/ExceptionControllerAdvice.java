package ru.mystudy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mystudy.dto.ErrorResponse;
import ru.mystudy.exception.CheckLimitException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CheckLimitException.class, IllegalArgumentException.class})
    public ErrorResponse checkLimitException(Exception exception) {
        return new ErrorResponse("Ошибка параметров запроса", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse internalErrorException(Exception exception) {
        return new ErrorResponse("Внутренняя ошибка на сервере", exception.getMessage());
    }

}
