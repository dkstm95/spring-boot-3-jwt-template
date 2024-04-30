package com.seungilahn.springboot3jwttemplate.common;

import org.springframework.http.HttpStatus;

public enum ApiStatus {

    OK(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase())
    , NO_CONTENT(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase())
    , BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
    , TIMEOUT(HttpStatus.GATEWAY_TIMEOUT.value(), HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
    ;

    private final int code;

    private final String message;

    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
