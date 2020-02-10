package com.mralmost.community.service;

import com.mralmost.community.dto.CommentReturnDTO;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.enums.CommentTypeEnum;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.mapper.CommentMapper;
import com.mralmost.community.mapper.QuestionCustomMapper;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionCustomMapper questionCustomMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加评论
     *
     * @param comment 评论信息
     */
    @Transactional
    public void insertSelective(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(ErrorCode.TARGET_PARENT_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomException(ErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomException(ErrorCode.COMMENT_NOT_FOUNT);
            }
            commentMapper.insertSelective(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            questionCustomMapper.incCommentCount(question);
        }
    }

    /**
     * 根据问题Id查询评论
     *
     * @param id 问题id
     * @return
     */
    public List<CommentReturnDTO> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0) {
            return null;
        }

        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //把上面拿到的评论人转换成userId
        ArrayList userIds = new ArrayList();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为commentReturnDTO
        List<CommentReturnDTO> commentReturnDTOList = comments.stream().map(comment -> {
            CommentReturnDTO commentReturnDTO = new CommentReturnDTO();
            BeanUtils.copyProperties(comment, commentReturnDTO);
            commentReturnDTO.setUser(userMap.get(comment.getCommentator()));
            return commentReturnDTO;
        }).collect(Collectors.toList());
        return commentReturnDTOList;
    }

    /**
     * 根据评论id删除评论,然后给问题的评论数-1
     *
     * @param id
     * @return
     */
    @Transactional
    public void deleteComment(Long id) {
        try {
            Comment comment = commentMapper.selectByPrimaryKey(id);
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            question.setCommentCount(question.getCommentCount() - 1);
            questionMapper.updateByPrimaryKeySelective(question);
            commentMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SYSTEM_ERROR);
        }
    }
}
