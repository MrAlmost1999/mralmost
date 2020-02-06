package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 评论DTO
 * @date: 2020/1/30
 */
@Data
public class CommentDTO {
    /**
     * 父类id
     */
    private Long parentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 父类类型
     */
    private Integer type;
}
