package com.seungilahn.springboot3jwttemplate.common;

public class ApiResponse<T> {

    private final T data;
    private final String message;
    private final int code;

    private ApiResponse(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, ApiStatus.OK.getMessage(), ApiStatus.OK.getCode());
    }

    public static <T> ApiResponse<T> fail(ApiStatus status) {
        return new ApiResponse<>(null, status.getMessage(), status.getCode());
    }

    public static ApiResponse<Void> noContent() {
        return new ApiResponse<>(null, ApiStatus.NO_CONTENT.getMessage(), ApiStatus.NO_CONTENT.getCode());
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
