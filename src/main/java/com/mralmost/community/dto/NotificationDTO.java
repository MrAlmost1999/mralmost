package com.mralmost.community.dto;

import com.mralmost.community.model.User;
import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO
 * @date: 2020/2/18
 */
@Data
public class NotificationDTO {

    private Long id;
    private String gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String type;
}
