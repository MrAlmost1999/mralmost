package com.mralmost.community.exception;

/**
 * @author Lxj
 * @Package com.mralmost.community.exception
 * @Description TODO 用于处理运行时异常
 * @date: 2020/1/22
 */
public class CustomException extends RuntimeException{
    private String massage;

    public CustomException(CustomErrorCode errorCode) {
        this.massage=errorCode.getMassage();
    }

    public CustomException(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
