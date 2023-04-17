package com.epam.training.kubernetes.dockerdemo.user.controller.advice;


import com.epam.training.kubernetes.dockerdemo.user.config.LocalizedMessageProvider;
import com.epam.training.kubernetes.dockerdemo.user.exception.BadRequestException;
import com.epam.training.kubernetes.dockerdemo.user.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final LocalizedMessageProvider messageProvider;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
        log.warn("handling filed validation exception: {}", ex.getMessage());
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors
                        .toMap(FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                (value1, value2) -> value1)
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(fieldErrors);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handleBadRequest(Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<?> handleDuplicateKey(Exception ex, WebRequest request) {
        return new ResponseEntity(messageProvider.getMessage("user.username.already_exists"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
