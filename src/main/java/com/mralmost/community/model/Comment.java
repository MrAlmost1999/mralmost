package com.mralmost.community.model;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.model
 * @Description TODO 评论实体类
 * @date: 2020/1/30
 */
@Data
public class Comment {
    /**
     * 评论主键
     */
    private Long id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 父类id
     */
    private Long parentId;
    /**
     * 父类类型
     */
    private Long type;
    /**
     * 评论人id
     */
    private Long commentator;
    /**
     * 评论时间
     */
    private Long gmtCreate;
    /**
     * 评论修改时间
     */
    private Long gmtModified;
    /**
     * 点赞数
     */
    private Long likeCount;
    /**
     * 评论数
     */
    private Long commentCount;

}
