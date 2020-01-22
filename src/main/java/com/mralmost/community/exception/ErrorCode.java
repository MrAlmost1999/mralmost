package com.mralmost.community.exception;

public enum ErrorCode implements CustomErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在或者已经删除了,要不再看看其他的吧?");

    private String massage;

    @Override
    public String getMassage() {
        return massage;
    }

    ErrorCode(String massage) {
        this.massage = massage;
    }
}
