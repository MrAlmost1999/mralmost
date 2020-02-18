package com.mralmost.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO
 * @date: 2020/2/18
 */

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
