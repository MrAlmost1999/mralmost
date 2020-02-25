package com.mralmost.community.exception;

public enum ErrorCode implements CustomErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在或者已经删除了,要不再看看其他的吧?"),
    TARGET_PARENT_NOT_FOUND(2002, "未选中任何问题或评论进行回复!"),
    NO_LOGIN(2003, "当前操作需要登录,请登录后重试!"),
    SYSTEM_ERROR(2004, "服务器冒烟了,要不你稍后再试试!"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在!"),
    COMMENT_NOT_FOUNT(2006, "回复的评论不存在了,要不要换个试试?"),
    COMMENT_IS_EMPTY(2007, "评论内容不能为空!"),
    READ_NOTIFICATION_FAIL(2008, "兄弟,你该不会是程序员吧?"),
    NOTIFICATION_NOT_FOUND(2009, "消息不翼而飞了?"),
    FILE_UPLOAD_FAIL(20010, "蹄片上传失败"),
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
