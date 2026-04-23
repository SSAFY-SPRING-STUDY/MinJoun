package org.example.springstudy.common.response;

import lombok.Getter;
import org.example.springstudy.common.exception.ErrorCode;

@Getter
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String s, T data) {
        this.message = s;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("요청에 성공했습니다.", data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>("요청에 성공했습니다.", null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getMessage(), null);
    }
}
