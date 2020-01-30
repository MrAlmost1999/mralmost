package com.mralmost.community.service;

import com.mralmost.community.mapper.CommentMapper;
import com.mralmost.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/1/30
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 添加评论
     *
     * @param comment 评论信息
     */
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
