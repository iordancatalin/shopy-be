package com.shopy.shopybe.exception;

import com.shopy.shopybe.exception.model.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorController {


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Error> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        final var error = Error.builder()
                .errorCode(ErrorCode.ERR_400)
                .timestamp(LocalDateTime.now())
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {
        final var error = Error.builder()
                .errorCode(ErrorCode.ERR_500)
                .timestamp(LocalDateTime.now())
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
