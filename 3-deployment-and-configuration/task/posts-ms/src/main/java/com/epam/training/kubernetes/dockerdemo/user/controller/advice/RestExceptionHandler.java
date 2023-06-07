package com.epam.training.kubernetes.dockerdemo.user.controller.advice;

import com.epam.training.kubernetes.dockerdemo.user.config.LocalizedMessageProvider;
import com.epam.training.kubernetes.dockerdemo.user.exception.BadRequestException;
import com.epam.training.kubernetes.dockerdemo.user.exception.InternalException;
import com.epam.training.kubernetes.dockerdemo.user.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.HttpHostConnectException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> handleBadRequest(Exception ex, HttpServletRequest request) {
        log.error("BadRequest exception caught. URL {}", request.getRequestURL(), ex);
        return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFound(Exception ex, HttpServletRequest request) {
        log.warn("NotFound exception caught. URL {}", request.getRequestURL(), ex);
        return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<?> handleRemoteServiceErrors(HttpClientErrorException ex, HttpServletRequest request) {
        log.warn("Remote service returns error. URL {}", request.getRequestURL(), ex);
        return new ResponseEntity(ex.getLocalizedMessage(), ex.getStatusCode());
    }

    @ExceptionHandler({HttpHostConnectException.class, InternalException.class})
    public ResponseEntity<?> handleInternalError(Exception ex, HttpServletRequest request) {
        log.error("Internal exception caught. URL {}", request.getRequestURL(), ex);
        return new ResponseEntity("Oops, internal error. Working on it. Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
