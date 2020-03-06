package com.mralmost.community.mapper;

import com.mralmost.community.model.Comment;

/**
 * @author Lxj
 * @Package com.mralmost.community.mapper
 * @Description TODO comment mapper的拓展类
 * @date: 2020/2/14
 */
public interface CommentCustomMapper {

    /**
     * 累加回复的回复数
     *
     * @param comment
     * @return
     */
    int incCommentCommentCount(Comment comment);

    /**
     * 删除二级评论时,减少回复数
     *
     * @param comment
     * @return
     */
    int reduceCommentCommentCount(Comment comment);
}
