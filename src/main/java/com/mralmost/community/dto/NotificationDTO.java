package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 通知的DTO数据传输对象
 * @date: 2020/2/18
 */
@Data
public class NotificationDTO {

    /**
     * 通知id
     */
    private Long id;
    /**
     * 状态码,0未读,1已读
     */
    private Integer status;
    /**
     * 通知创建时间
     */
    private String gmtCreate;
    /**
     * 通知人
     */
    private Long notifier;
    /**
     * 通知人昵称
     */
    private String notifierName;
    /**
     * 外部标题
     */
    private String outerTitle;
    /**
     * 外部id
     */
    private Long outerId;
    /**
     * 类型名称:回复了评论or回复了问题
     */
    private String typeName;
    /**
     * 类型
     */
    private Integer type;
}
