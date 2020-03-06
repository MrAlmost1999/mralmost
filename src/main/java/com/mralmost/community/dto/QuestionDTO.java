package com.mralmost.community.dto;

import com.mralmost.community.model.User;
import lombok.Data;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO 问题实体表拓展类
 * @date: 2020/1/14
 */
@Data
public class QuestionDTO {
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
    private String createTime;
    /**
     * 问题修改时间
     */
    private String modifiedTime;
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
     * 问题标签
     */
    private String tag;
    /**
     * 发布人类别
     */
    private String publishType;
    /**
     * 问题发布用户信息
     */
    private User user;
    /**
     * 搜索条件
     */
    private String search;

}
