package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.provider
 * @Description TODO
 * @date: 2020/1/9
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
