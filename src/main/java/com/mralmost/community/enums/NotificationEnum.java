package com.mralmost.community.enums;

import com.mralmost.community.model.Notification;

/**
 * @author Lxj
 * @Package com.mralmost.community.enums
 * @Description TODO 用于显示通知回复类型的enum
 * @date: 2020/2/18
 */
public enum NotificationEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");


    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public static String nameOfType(int type) {
        for (NotificationEnum notificationEnum : NotificationEnum.values()) {
            if (notificationEnum.getType() == type) {
                return notificationEnum.getName();
            }
        }
        return "";
    }
}
