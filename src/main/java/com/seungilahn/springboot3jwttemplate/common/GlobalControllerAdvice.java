package com.seungilahn.springboot3jwttemplate.common;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntimeException(RuntimeException e) {
        log.error("Runtime exception: ", e);
        return ApiResponse.fail(ApiStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<?> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Entity not found: ", e);
        return ApiResponse.fail(ApiStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument: ", e);
        return ApiResponse.fail(ApiStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Method argument type mismatch: ", e);
        return ApiResponse.fail(ApiStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Method argument not valid: ", e);
        return ApiResponse.fail(ApiStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("Missing servlet request parameter: ", e);
        return ApiResponse.fail(ApiStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ApiResponse<?> handleIllegalStateException(IllegalStateException e) {
        log.error("Illegal state: ", e);
        return ApiResponse.fail(ApiStatus.INTERNAL_SERVER_ERROR);
    }

}
