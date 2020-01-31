package com.mralmost.community.model;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.Model
 * @Description TODO
 * @date: 2020/1/10
 */
@Data
public class User {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户关联getHub的用户id
     */
    private String accountId;
    /**
     * 用户令牌
     */
    private String token;
    /**
     * 用户创建时间
     */
    private Long gmtCreate;
    /**
     * 用户修改id
     */
    private Long gmtModified;
    /**
     * 用户头像地址
     */
    private String avatarUrl;
}
