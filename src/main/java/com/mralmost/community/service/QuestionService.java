package com.mralmost.community.service;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<QuestionDTO> findAll() {
        return questionMapper.findAll();
    }

    public List<QuestionDTO> findByCreator(Integer id) {
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
            questionMapper.update(question);
        }
    }

    public QuestionDTO findById(Integer id) {
        return questionMapper.findById(id);
    }
}
