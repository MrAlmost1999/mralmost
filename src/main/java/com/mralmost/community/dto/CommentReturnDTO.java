package com.mralmost.community.dto;

import com.mralmost.community.model.User;
import lombok.Data;

import java.util.Date;

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
    private Date gmtCreate;
    private Date gmtModified;
    private Long likeCount;
    private Long commentCount;
    private String content;
    private User user;
}
