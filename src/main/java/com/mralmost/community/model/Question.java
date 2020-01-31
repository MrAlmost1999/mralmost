package com.mralmost.community.model;

import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.model
 * @Description TODO 问题实体表
 * @date: 2020/1/12
 */
@Data
public class Question {
    /**
     * 问题id
     */
    private Long id;
    /**
     * 问题标题
     */
    private String title;
    /**
     * 问题描述
     */
    private String description;
    /**
     * 问题创建时间
     */
    private Long gmtCreate;
    /**
     * 问题修改时间
     */
    private Long gmtModified;
    /**
     * 问题关联用户的id
     */
    private Long creator;
    /**
     * 问题访问数
     */
    private Integer viewCount;
    /**
     * 问题回复数
     */
    private Integer commentCount;
    /**
     * 问题点赞数
     */
    private Integer likeCount;
    /**
     * 问题描述
     */
    private String tag;

}
