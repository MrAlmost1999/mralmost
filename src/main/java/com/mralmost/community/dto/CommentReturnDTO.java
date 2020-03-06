package com.mralmost.community.dto;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 后端返回的评论DTO
 * @date: 2020/2/9
 */
@Data
public class CommentReturnDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private String createTime;
    private String modifiedTime;
    private Long likeCount;
    private Long commentCount;
    private String content;
    private Object user;
}
