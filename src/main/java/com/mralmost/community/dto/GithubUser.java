package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.provider
 * @Description TODO gitHub的用户信息表
 * @date: 2020/1/9
 */
@Data
public class GithubUser {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户个人简历
     */
    private String bio;
    /**
     * 用户头像地址
     */
    private String avatarUrl;
}
