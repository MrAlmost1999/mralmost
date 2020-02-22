package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.dto.CommentReturnDTO;
import com.mralmost.community.enums.CommentTypeEnum;
import com.mralmost.community.enums.NotificationEnum;
import com.mralmost.community.enums.NotificationStatusEnum;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.mapper.*;
import com.mralmost.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private CommentCustomMapper commentCustomMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 添加评论
     *
     * @param comment
     * @param commentator
     */
    @Transactional
    public void insertSelective(Comment comment, User commentator) {
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
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
            }
            //插入回复
            commentMapper.insertSelective(comment);
            //累加回复数
            commentCustomMapper.incCommentCommentCount(comment);
            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationEnum.REPLY_COMMENT, question.getId());
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            questionCustomMapper.incQuestionCommentCount(question);
            //创建通知
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationEnum.REPLY_QUESTION, question.getId());
        }
    }

    /**
     * 创建通知
     *
     * @param comment
     * @param receiver
     * @param notifierName
     * @param outerTitle
     * @param notificationEnum
     * @param outerId
     */
    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationEnum notificationEnum, Long outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(DateFormat.dateFormat(new Date()));
        notification.setType(notificationEnum.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 根据问题Id查询评论
     *
     * @param id   问题id
     * @param type
     * @return
     */
    public List<CommentReturnDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create asc");
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
            if (comment.getType() == 1) {
                Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
                question.setCommentCount(question.getCommentCount() - 1);
                questionMapper.updateByPrimaryKeySelective(question);
            } else {
                commentCustomMapper.reduceCommentCommentCount(comment);
            }
            commentMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SYSTEM_ERROR);
        }
    }
}
