package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.mapper.QuestionCustomMapper;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.RecordMapper;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.service
 * @Description TODO
 * @date: 2020/1/14
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionCustomMapper questionCustomMapper;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> findAll() {
        return questionCustomMapper.selectQuestionWithUser();
    }

    public List<QuestionDTO> findByCreator(Long creator) {
        return questionCustomMapper.selectQuestionWithUserByCreator(creator);
    }

    /**
     * id为主键自增长列,当id为null时,为插入操作,执行插入方法,不为null时为更新操作,执行更新方法
     *
     * @param question 插入或更新的信息
     */
    public void insertOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.insertSelective(question);
        } else {
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }

    /**
     * 根据id查询问题
     *
     * @param id 问题主键id
     * @return
     */
    public QuestionDTO findById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 根据问题id累加阅读数
     *
     * @param record
     */
    public void updateViewCount(Record record) {
        //先查询该用户是否有浏览过此条记录,没有过则累加阅读数,有则修改浏览时间
        boolean flag = recordService.insertOrUpdate(record);
        if (!flag) {
            Question question = new Question();
            question.setGmtModified(System.currentTimeMillis());
            question.setId(record.getQuestionId());
            questionCustomMapper.updateViewCountAndGmtModified(question);
        }
    }
}
