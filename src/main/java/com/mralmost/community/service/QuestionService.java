package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.mapper.RecordMapper;
import com.mralmost.community.model.Question;
import com.mralmost.community.model.Record;
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
    private RecordService recordService;

    public List<QuestionDTO> findAll() {
        return questionMapper.findAll();
    }

    public List<QuestionDTO> findByCreator(Long id) {
        return questionMapper.findByCreator(id);
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
            questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.edit(question);
        }
    }

    /**
     * 根据id查询问题
     *
     * @param id 问题主键id
     * @return
     */
    public QuestionDTO findById(Long id) {
        QuestionDTO question = questionMapper.findById(id);
        if (question == null) {
            throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        }
        return question;
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
            questionMapper.updateViewCount(record.getQuestionId());
        }
    }
}
