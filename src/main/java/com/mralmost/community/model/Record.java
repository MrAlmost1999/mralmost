package com.mralmost.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Lxj
 * @Package com.mralmost.community.model
 * @Description TODO 浏览记录表
 * @date: 2020/1/23
 */
@Data
public class Record {
    /**
     * 记录id
     */
    private Long id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 问题id
     */
    private Integer questionId;
    /**
     * 浏览时间
     */
    private Date recordDate;

}
