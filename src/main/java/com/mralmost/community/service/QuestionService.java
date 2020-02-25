package com.mralmost.community.service;

import com.mralmost.community.date.DateFormat;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.mapper.QuestionCustomMapper;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.RecordMapper;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 查询所有问题
     *
     * @return
     */
    public List<QuestionDTO> findQuestion(String search) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSearch(search);
        return questionCustomMapper.selectQuestionWithUser(questionDTO);
    }

    /**
     * 根据问题的creator列查询问题和相关用户信息
     *
     * @param creator
     * @return
     */
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
            question.setGmtCreate(DateFormat.dateFormat(new Date()));
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
            question.setGmtModified(DateFormat.dateFormat(new Date()));
            question.setId(record.getQuestionId());
            questionCustomMapper.updateViewCountAndGmtModified(question);
        }
    }

    /**
     * 根据标签查询相关的内容
     *
     * @param queryDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(queryDTO.getTag().replace(",", "|"));
        List<Question> questions = questionCustomMapper.selectRelated(question);
        List<QuestionDTO> questionDTOList = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOList;
    }

    /**
     * 查询最热问题
     *
     * @return
     */
    public List<Question> selectByHottest() {
        return questionCustomMapper.selectByHottest();
    }

    /**
     * 查询最新问题
     *
     * @return
     */
    public List<Question> selectByNewset() {
        return questionCustomMapper.selectByNewset();
    }

    public void deleteByPrimaryKey(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }

}
