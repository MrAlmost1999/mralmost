package com.mralmost.community.enums;

/**
 * @author Lxj
 * @Package com.mralmost.community.enums
 * @Description TODO 用于显示通知回复类型的enum
 * @date: 2020/2/18
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);

    private int status;

    public int getStatus() {
        return status;
    }


    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
