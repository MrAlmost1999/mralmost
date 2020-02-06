package com.mralmost.community.exception;

/**
 * @author Lxj
 * @Package com.mralmost.community.exception
 * @Description TODO 用于处理运行时异常
 * @date: 2020/1/22
 */
public class CustomException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomException(CustomErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
