package com.example.bookbackend.service.api;

import com.example.bookbackend.service.exception.BadRequestException;
import com.example.bookbackend.service.exception.EntityNotFoundException;
import com.example.bookbackend.service.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class SecurityControllerAdvice extends ResponseEntityExceptionHandler {
    private static final String REASON_NOT_FOUND = "Entity Not Found";
    private static final String BAD_REQUEST_EXCEPTION = "Bad Request Exception";
    private static final String REASON_INTERNAL_ERROR = "Internal Error";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleEntityNotFoundException(EntityNotFoundException exception) {
        Error error = new Error();

        error.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(REASON_NOT_FOUND);
        error.setSeeAlso("seeAlsoNotFoundUrl");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleEntityBadRequestException(BadRequestException exception) {
        Error error = new Error();

        error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(BAD_REQUEST_EXCEPTION);
        error.setSeeAlso("seeAlsoBadRequestUrl");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleInternalErrorException(Exception exception) {
        Error error = new Error();

        error.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(REASON_INTERNAL_ERROR);
        error.setSeeAlso("seeAlsoInternalErrorUrl");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}