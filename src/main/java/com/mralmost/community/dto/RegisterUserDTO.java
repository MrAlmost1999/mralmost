package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 注册用户的DTO
 * @date: 2020/3/4
 */
@Data
public class RegisterUserDTO {

    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 激活码
     */
    private String code;


}
