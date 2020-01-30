package com.mralmost.community.mapper;

import com.mralmost.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO 评论mapper
 * @date: 2020/1/30
 */
@Mapper
public interface CommentMapper {

    /**
     * 添加评论
     *
     * @param comment 评论信息
     */
    @Insert("insert into comment values(#{content},#{parentId},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{likeCount},#{commentCount})")
    void insert(Comment comment);
}
