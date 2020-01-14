package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO
 * @date: 2020/1/9
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
